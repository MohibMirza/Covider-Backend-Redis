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


        User john = new User("John");
        john.setClass1("csci_103");
        csci_103.addStudent("John");
        john.setClass2("ee-109");
        ee_109.addStudent("John");
        john.setFirstName("Johnny");
        john.setLastName("Test");

        User mark = new User("Mark");
        mark.setClass1("ee-109");
        ee_109.addStudent("Mark");
        mark.setClass2("csci_310");
        csci_310.addStudent("Mark");
        mark.setClass3("csci_104");
        csci_104.addStudent("Mark");
        mark.setFirstName("Marko");
        mark.setLastName("Jacoby");

        User sarah = new User("Sarah");
        sarah.setClass1("ee-109");
        ee_109.addStudent("Sarah");
        sarah.setClass2("csci-104");
        csci_104.addStudent("");
        sarah.setIsInstructor(true);
        sarah.setFirstName("Sarah");
        sarah.setLastName("Hopkins");
        sarah.setCovidStatus(Status.infected);

        Building building = new Building("RTH");
        building.setLatitude(0.0);
        building.setLongitude(0.0);
        Building building2 = new Building("SAL");
        building2.setLatitude(0.0);
        building2.setLongitude(0.0);
        Building building3 = new Building("RTH");
        building3.setLatitude(0.0);
        building3.setLongitude(0.0);

        User joe = new User("joe");
        joe.setPassword("test");


    }
}
