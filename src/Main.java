import redis.RClass.Building;
import redis.RClass.User;
import org.redisson.api.RLiveObjectService;
import redis.RedisClient;

import java.util.Date;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
    }
}
