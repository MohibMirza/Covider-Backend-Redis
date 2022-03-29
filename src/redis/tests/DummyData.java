package redis.tests;

import redis.RClass.Building;
import redis.RClass.Class;
import redis.RClass.Status;
import redis.RClass.User;
import redis.RedisClient;

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

        User john = new User("John");
        john.setClass1("csci_103");
        csci_103.addStudent("John");
        john.setClass2("ee-109");
        ee_109.addStudent("John");
        john.setFirstName("Johnny");
        john.setLastName("Test");
        john.setPassword("1234");

        User mark = new User("Mark");
        mark.setClass1("ee-109");
        ee_109.addStudent("Mark");
        mark.setClass2("csci_310");
        csci_310.addStudent("Mark");
        mark.setClass3("csci_104");
        csci_104.addStudent("Mark");
        mark.setFirstName("Marko");
        mark.setLastName("Jacoby");
        mark.setPassword("1234");


        User sarah = new User("Sarah");
        sarah.setClass1("ee-109");
        ee_109.addStudent("Sarah");
        sarah.setClass2("csci-104");
        csci_104.addStudent("");
        sarah.setIsInstructor(true);
        sarah.setFirstName("Sarah");
        sarah.setLastName("Hopkins");
        sarah.setCovidStatus(Status.infected);
        sarah.setPassword("1234");


        Building building = new Building("SAL");
        building.setLatitude(34.0187);
        building.setLongitude(-118.2826);
        Building building2 = new Building("SSL");
        building2.setLatitude(34.0196);
        building2.setLongitude(-118.2888);
        Building building3 = new Building("RTH");
        building3.setLatitude(34.0201);
        building3.setLongitude(-118.2899);
        Building building4 = new Building("JFF");
        building4.setLatitude(34.0195);
        building4.setLongitude(-118.2895);
        Building building5 = new Building("HSH");
        building5.setLatitude(34.0204);
        building5.setLongitude(-118.2871);



        User joe = new User("joe");
        joe.setPassword("test");

        redisClient.shutdown();

    }
}
