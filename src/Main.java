import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.redisson.Redisson;
import org.redisson.api.RLiveObjectService;
import org.redisson.api.RedissonClient;
import org.redisson.api.annotation.REntity;
import org.redisson.api.annotation.RId;
import org.redisson.liveobject.resolver.LongGenerator;
import org.redisson.liveobject.resolver.UUIDGenerator;

public class Main {


    public static void main(String[] args) {
        // connects to 127.0.0.1:6379 by default
        RedissonClient redisson = Redisson.create();

        RLiveObjectService liveObjectService = redisson.getLiveObjectService();

        
        // WRITE TEST CODE HERE



        redisson.shutdown();
    }

}
