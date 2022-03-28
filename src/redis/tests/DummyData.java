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
        john.setClass2("ee-109");
        john.setFirstName("Johnny");
        john.setLastName("Test");

        User mark = new User("Mark");
        mark.setClass1("ee-109");
        mark.setClass2("csci_310");
        mark.setClass3("csci_104");
        mark.setFirstName("Marko");
        mark.setLastName("Jacoby");

        User sarah = new User("Sarah");
        sarah.setClass1("ee-109");
        sarah.setClass2("csci-104");
        sarah.setIsInstructor(true);
        sarah.setFirstName("Sarah");
        sarah.setLastName("Hopkins");
        sarah.setCovidStatus(Status.infected);

        Building building = new Building("RTH");
        Building building2 = new Building("SAL");
        Building building3 = new Building("RTH");

    }
}
