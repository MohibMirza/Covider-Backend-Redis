package redis.tests;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.AfterClass;
import static org.junit.Assert.*;

import org.redisson.api.RLiveObjectService;
import redis.RClass.Building;
import redis.RClass.User;
import redis.RClass.Visit;
import redis.RedisClient;

import java.util.Date;

import static redis.RedisDatabase.*;

public class RedisTest {

    public static RedisClient redis;
    public static RLiveObjectService rlo;

    @BeforeClass
    public static void init() {
        redis = new RedisClient("redis://127.0.0.1:6379");
        redis.start();
        rlo = redis.redisson.getLiveObjectService();
        rlo.unregisterClass(User.class);
        rlo.unregisterClass(Building.class);
        rlo.registerClass(User.class);
        rlo.registerClass(Building.class);
    }

    @Before
    public void setup() {
        System.out.println("Performing Test");
    }

    @Test
    public void userAddThenDeleteTest() {
        String username = "MyUser";

        User user = getOrCreateUser(username);

        assertNotNull(user);

        deleteUser(username);

        user = rlo.get(User.class, username);

        assertNull(user);
    }

    public static String password = "applejack";
    public static boolean isInstructor = true;

    @Test
    public void userDataTest() throws InterruptedException {
        String username = "ujkihi";
        long timestamp = System.currentTimeMillis();

        User user = getOrCreateUser(username);
        assertNotNull(user);

        user.setPassword(password);
        user.setIsInstructor(isInstructor);
        user.getVisitedBuildings().add("Home");


        User alteredUser = getOrCreateUser(username);

        assertEquals(password, alteredUser.getPassword());
        assertEquals(isInstructor, user.getIsInstructor());
        assertEquals('0', alteredUser.getCovidStatus().getStatus());
        assertEquals("Home", user.getVisitedBuildings().get(0));
        assertEquals("John", alteredUser.getFirstName());

        rlo.delete(user);
        user = rlo.get(User.class, username);
        assertNull(user);

    }

    @Test
    public void buildingAddThenDeleteTest() {
        String buildingName = "SAL";
        String visitorId = "dewy";
        Building building = getOrCreateBuilding(buildingName);

        building.setRiskScore(5.0);
        building.getVisits().add(new Visit(visitorId));

        Building alteredBuilding = getOrCreateBuilding(buildingName);

        assertEquals(5.0, alteredBuilding.getRiskScore(), 0.0);
        assertEquals(1, alteredBuilding.getVisits().size());
        assertEquals(visitorId, alteredBuilding.getVisits().get(0).getUserId());

        // Makes sure the current date is after the date in the visit.
        assertTrue((new Date()).after(alteredBuilding.getVisits().get(0).getDate()));

        deleteBuilding(buildingName);

        building = rlo.get(Building.class, buildingName);
        assertNull(building);

    }

    @Test
    public void userVisitsBuildingTest() {

    }

    @AfterClass
    public static void end() {
        redis.shutdown();
    }
}
