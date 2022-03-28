package redis.RClass;

import org.redisson.api.RList;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import redis.RedisClient;

import java.util.List;

public class Class {

    String className;
    String name;

    private static RedissonClient redisson;

    public Class(String name) {
        this.className = name;
        this.name = "class." + name.toLowerCase();

        if (redisson == null) { redisson = RedisClient.getInstance().redisson; }

        if(!redisson.getBucket(name + ".isInPerson").isExists()) {
            redisson.getBucket(name + ".isInPerson").set(true);
            redisson.getSet(name + ".students");
        }
    }

    public String getClassName() {
        return className;
    }

    public boolean getInPerson() {
        return (Boolean) redisson.getBucket(name + ".isInPerson").get();
    }

    public List<String> getStudents() {
        RSet<String> rList = redisson.getSet(name + ".students");
        List<String> students = rList.stream().toList();
        return students;
    }

    public void addStudent(String userId) {
        userId = userId.toLowerCase();
        redisson.getSet(name + ".students").add(userId);
    }

    public void setInPerson(boolean willBeInPerson) {
        redisson.getBucket(name + ".isInPerson").set(willBeInPerson);
    }

    public void delete() {

        redisson.getBucket(name + ".isInPerson").delete();
        redisson.getList(name + ".students").clear();
        redisson.getList(name + ".students").delete();
    }




}
