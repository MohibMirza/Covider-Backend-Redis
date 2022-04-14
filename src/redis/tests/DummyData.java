package redis.tests;

import redis.RClass.Building;
import redis.RClass.Class;
import redis.RClass.Status;
import redis.RClass.User;
import redis.RedisClient;
import redis.RedisDatabase;

import java.util.List;
import java.util.Random;

public class DummyData {

    public static void main(String[] args) {
        RedisClient redisClient = new RedisClient("redis://127.0.0.1:6379");
        redisClient.start();

        // Classes
        Class csci_103 = new Class("csci_103");
        Class csci_104 = new Class("csci_104");
        Class ee_109 = new Class("ee-109");
        Class csci_310 = new Class("csci-310");


        User sam = new User("Sam");
        sam.setClass1("csci_103");
        csci_103.addStudent("Sam");
        sam.setClass2("ee-109");
        ee_109.addStudent("Sam");
        sam.setFirstName("Sam");
        sam.setLastName("Hill");
        sam.setPassword("1234");
        randomizeVisits(sam);

        String johnEmail = "john@gmail.com";
        User john = new User(johnEmail);
        john.setClass1("csci_103");
        csci_103.addStudent(johnEmail);
        john.setClass2("ee-109");
        ee_109.addStudent(johnEmail);
        john.setFirstName("Johnny");
        john.setLastName("Test");
        john.setPassword("1234");
        randomizeVisits(john);

        String markEmail = "mark@gmail.com";
        User mark = new User(markEmail);
        mark.setClass1("ee-109");
        ee_109.addStudent(markEmail);
        mark.setClass2("csci_310");
        csci_310.addStudent(markEmail);
        mark.setClass3("csci_104");
        csci_104.addStudent(markEmail);
        mark.setFirstName("Marko");
        mark.setLastName("Jacoby");
        mark.setPassword("1234");
        randomizeVisits(mark);

        String sarahEmail = "sarah@gmail.com";
        User sarah = new User(sarahEmail);
        sarah.setClass1("ee-109");
        ee_109.addStudent(sarahEmail);
        sarah.setClass2("csci_104");
        csci_104.addStudent(sarahEmail);
        sarah.setIsInstructor(true);
        sarah.setFirstName("Sarah");
        sarah.setLastName("Hopkins");
        sarah.setCovidStatus(Status.healthy);
        sarah.setPassword("1234");
        randomizeVisits(sarah);


        Building building = new Building("JFF");
        building.setLatitude(34.0187);
        building.setLongitude(-118.2826);
        building.setInstructions("Please wear double masks");
        Building building2 = new Building("SSL");
        building2.setLatitude(34.0196);
        building2.setLongitude(-118.2888);
        building2.setInstructions("Wear green only");
        Building building3 = new Building("RTH");
        building3.setLatitude(34.0201);
        building3.setLongitude(-118.2899);
        building3.setInstructions("Enter thru front");
        Building building4 = new Building("SAL");
        building4.setLatitude(34.0195);
        building4.setLongitude(-118.2895);
        Building building5 = new Building("HSH");
        building5.setLatitude(34.0204);
        building5.setLongitude(-118.2871);



        User joe = new User("joe");
        joe.setPassword("test");

        redisClient.shutdown();

    }

    public static void randomizeVisits(User user) {
        List<String> buildings = RedisDatabase.buildingNames;
        Random random = new Random();
        for(String s : buildings) {
            int Min = 0;
            int Max = 100;
            int val = Min + (int)(Math.random() * ((Max - Min) + 1));
            user.addVisits(s, val);
//            System.out.println(user.getBuildingVisitCount(s));
//            System.out.println(s);
        }
    }
}
