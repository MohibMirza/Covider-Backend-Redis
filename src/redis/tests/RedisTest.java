package redis.tests;

import org.junit.*;

import static org.junit.Assert.*;

import org.redisson.api.RKeys;
import org.redisson.api.RLiveObjectService;
import redis.RClass.*;
import redis.RedisClient;

import java.util.Date;

import static redis.RedisDatabase.*;

public class RedisTest {

    public static RedisClient redis;

    public static long keyCount;

    @BeforeClass
    public static void init() {
        redis = new RedisClient("redis://127.0.0.1:6379");
        redis.start();
    }

    @Before
    public void before() {
        RKeys rKeys = redis.redisson.getKeys();
        keyCount = rKeys.count();
    }

    @After
    public void after() {
        RKeys rKeys = redis.redisson.getKeys();
        assertEquals(keyCount, rKeys.count());
    }

    @Test
    public void userAddThenDeleteTest() {
        User user = new User("Mark");
        assertEquals("", user.getPassword());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals(false, user.getIsInstructor());
        assertEquals("healthy", user.getCovidStatus());
        assertEquals("", user.getClass1());
        assertEquals("", user.getClass2());
        assertEquals("", user.getClass3());
        assertEquals("", user.getClass4());
        assertEquals("", user.getClass5());


        user.setPassword("password123");
        user.setFirstName("Joe");
        user.setLastName("Carr");
        user.setIsInstructor(true);
        user.setCovidStatus(Status.infected);
        user.setClass1("CSCI-103");
        user.setClass2("CSCI-104");
        user.setClass3("CSCI-109");
        user.setClass4("EE-109");
        user.setClass5("PHYS-151");

        User user1 = new User("Mark");

        assertEquals("password123", user1.getPassword());
        assertEquals("Joe", user1.getFirstName());
        assertEquals("Carr", user1.getLastName());
        assertEquals(true, user1.getIsInstructor());
        assertEquals("infected", user1.getCovidStatus());
        assertEquals("CSCI-103", user1.getClass1());
        assertEquals("CSCI-104", user1.getClass2());
        assertEquals("CSCI-109", user1.getClass3());
        assertEquals("EE-109", user1.getClass4());
        assertEquals("PHYS-151", user1.getClass5());

        user.delete();


    }

    public static String password = "applejack";
    public static boolean isInstructor = true;

    @Test
    public void userDataTest() throws InterruptedException {


    }

    @Test
    public void buildingAddThenDeleteTest() throws InterruptedException {
        Building building = new Building("RTH");
        assertEquals(1000.0, building.getRiskScore(), 0.0);
        building.setRiskScore(990.0);
        assertEquals(990.0, building.getRiskScore(), 0.0);


        Building building1 = new Building("RTH");
        assertEquals(990.0, building1.getRiskScore(), 0.0);

        building1.decrementRiskScore(5);
        assertEquals(985.0, building1.getRiskScore(), 0.0);

        building.delete();

    }

   @Test
    public void userVisitsBuildingTest() {

        Building building = new Building("SAL");
        building.addLastVisit("Jake", "2022-03-25");
        assertTrue(building.checkIfVisitedWithin10Days("Jake"));

        User user = new User("Jake");
        user.addVisit("SAL");
        assertEquals(1, user.getBuildingVisitCount("SAL"));
        user.addVisit("SAL");
        assertEquals(2, user.getBuildingVisitCount("SAL"));

        user.setCovidStatus(Status.symptomatic);
        assertEquals(998.0, building.getRiskScore(), 0.0);

        building.delete();
        user.delete();

    }

    @AfterClass
    public static void end() {
        redis.shutdown();
    }
}
