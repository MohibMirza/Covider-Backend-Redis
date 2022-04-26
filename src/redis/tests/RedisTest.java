package redis.tests;

import org.junit.*;

import static org.junit.Assert.*;

import org.redisson.api.RKeys;
import redis.RClass.*;
import redis.RClass.Class;
import redis.RedisClient;

import java.util.ArrayList;
import java.util.List;

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
        building.addLastVisit("Jake", "2022-04-20");
        assertTrue(building.checkIfVisitedWithin10Days("Jake"));

        User user = new User("Jake");
        user.addVisit("SAL");
        assertEquals(1, user.getBuildingVisitCount("SAL"));
        user.addVisit("SAL");
        assertEquals(2, user.getBuildingVisitCount("SAL"));

        user.setCovidStatus(Status.symptomatic);
        assertEquals(998.0, building.getRiskScore(), 0.0);

        user.setCovidStatus(Status.symptomatic);
        assertEquals(998.0, building.getRiskScore(), 0.0);

        building.delete();
        user.delete();

    }

    @Test
    public void userVisitsBuildingSimple() {
        Building building = new Building("Leavey");
        building.addVisit("Jonny");

        User user = new User("Jonny");
        user.addVisit("Leavey");
        user.setCovidStatus(Status.infected);


        assertEquals(995.0, building.getRiskScore(), 0.0);

        building.delete();
        user.delete();
    }

    // @Test
    public void addStudentToClass() {
        Class csci = new Class("csci");
        csci.addStudent("Jackson");

        List<String> students = csci.getStudents();


        assertEquals(1, students.size());

        csci.delete();

    }

    @Test
    public void tommyClassCheck() {
        Class csci = new Class("csci");
        assertNotNull(csci.getInPerson());
        csci.delete();
    }

    @Test
    public void instructorGotCovidTest() {
        Class phys = new Class("phys");
        User user = new User("kevin");
        user.setIsInstructor(true);
        user.setClass1("phys");
        phys.addStudent("kevin");

        user.setCovidStatus(Status.infected);

        assertEquals(false, phys.getInPerson());

        phys.delete();
        user.delete();
    }

    @Test
    public void userSendNotificationTest() {
        User user1 = new User("Jacko");
        User user2 = new User("James");
        Class class1 = new Class("psyc-100");
        List<String> userIds = new ArrayList<>();
        userIds.add("Jacko");
        userIds.add("James");
        List<String> classIds = new ArrayList<>();
        classIds.add("psyc-100");

        Notification notification = new Notification("Hello", userIds, classIds);
        notification.send();
        assertEquals(1, user1.getNotifications().size());
        assertEquals(1, user2.getNotifications().size());
        assertEquals(1, class1.getNotifications().size());
        assertEquals("Hello", user2.getNotifications().get(0));
        assertEquals("Hello", user1.getNotifications().get(0));
        assertEquals("Hello", class1.getNotifications().get(0));

        user1.delete();
        user2.delete();
        class1.delete();
    }

    @Test
    public void covidNotificationTest() {
        Class class1 = new Class("psyc-100");
        User user1 = new User("Jacko");
        user1.setClass1("psyc-100");
        User user2 = new User("Sam");
        Building building = new Building("RTH");

        user1.addVisit("RTH");
        user2.addVisit("RTH");
        building.addVisit("jacko");
        building.addVisit("Sam");

        user1.setCovidStatus(Status.infected);

        assertEquals(1, user2.getNotifications().size());
        assertEquals(1, class1.getNotifications().size());
        System.out.print(user2.getNotifications().get(0));


        user1.delete();
        user2.delete();
        building.delete();
        class1.delete();


    }

    @AfterClass
    public static void end() {
        redis.shutdown();
    }
}
