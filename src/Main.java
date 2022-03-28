import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.redisson.Redisson;
import org.redisson.api.RLiveObjectService;
import org.redisson.api.RedissonClient;
import org.redisson.api.annotation.REntity;
import org.redisson.api.annotation.RId;
import org.redisson.codec.SerializationCodec;
import org.redisson.config.Config;
import org.redisson.liveobject.resolver.LongGenerator;
import org.redisson.liveobject.resolver.UUIDGenerator;
import redis.RClass.User;
import redis.RedisClient;

import static redis.RedisDatabase.*;

public class Main {


    public static void main(String[] args) {
        // connects to 127.0.0.1:6379 by default
        RedisClient redisClient = new RedisClient("redis://127.0.0.1:6379");
        redisClient.start();
//
//        RLiveObjectService liveObjectService = redisClient.redisson.getLiveObjectService();

//        Calendar cal = Calendar.getInstance();
//
//        for(int i = 0; i < 7; i++) {
//            cal.roll(Calendar.DATE, false);
//        }
//        // 7 day prior date
//        Date pastDate = cal.getTime();
//        for(int i = 0; i < 7; i++) {
//            cal.roll(Calendar.DATE, true);
//        }
//
//        String strDate = "2022-03-22";
//
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//
//        try {
//            Date parsedDate = formatter.parse(strDate);
//            if(parsedDate.after(pastDate)){
//                System.out.println("true");
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        redisClient.redisson.getMap("Test").put("YOO", "YOYO");
        System.out.print((String) redisClient.redisson.getMap("Test").get("YOO"));

    }

}
