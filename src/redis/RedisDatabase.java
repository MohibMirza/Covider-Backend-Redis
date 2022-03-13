package redis;

import org.redisson.RedissonLiveObjectService;
import org.redisson.api.RLiveObjectService;
import redis.RClass.Building;
import redis.RClass.User;

public class RedisDatabase {

    public static RLiveObjectService rlo = RedisClient.getInstance().redisson.getLiveObjectService();

    public static Building getOrCreateBuilding(String buildingId) {
        if(rlo == null) { System.err.println("Redis Live Object Service Null!"); }
        Building building = rlo.get(Building.class, buildingId);
        if(building == null) {
            building = new Building(buildingId);
            rlo.persist(building);
            building = rlo.get(Building.class, buildingId);
        }

        return building;
    }

    public static User getOrCreateUser(String userId) {
        if(rlo == null) { System.err.println("Redis Live Object Service Null!"); }
        User user = rlo.get(User.class, userId);
        if(user == null) {
            user = new User(userId);
            rlo.persist(user);
            user = rlo.get(User.class, userId);
        }

        return user;
    }

    public static void deleteBuilding(String buildingId) {
        Building building = rlo.get(Building.class, buildingId);
        if(building != null) {
            rlo.delete(building);
        }
    }

    public static void deleteUser(String userId)  {
        User user = rlo.get(User.class, userId);
        if(user != null) {
            rlo.delete(user);
        }
    }
}
