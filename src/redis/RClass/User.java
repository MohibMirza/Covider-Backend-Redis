package redis.RClass;

import org.redisson.api.annotation.REntity;
import org.redisson.api.annotation.RId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@REntity
public class User implements Serializable {

    @RId
    private String userId;
    private String password;
    private String firstName;
    private String lastName;

    private String class1;
    private String class2;
    private String class3;
    private String class4;

    private List<String> visitedBuildings;
    private boolean isInstructor;

    private CovidStatus covidStatus;

    protected User() { }

    public User(String userId) {
        this.userId = userId;
        this.password = "";
        this.firstName = "John";
        this.lastName = "Doe";
        this.class1 = "";
        this.class2 = "";
        this.class3 = "";
        this.class4 = "";
        this.visitedBuildings = new ArrayList<String>();
        this.isInstructor = false;
        this.covidStatus = new CovidStatus(Status.healthy, new Date());
    }

    public String getUserId(){
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getClass1() {
        return class1;
    }

    public String getClass2() {
        return class2;
    }

    public String getClass3() {
        return class3;
    }

    public String getClass4() {
        return class4;
    }

    public CovidStatus getCovidStatus() {
        return covidStatus;
    }

    public List<String> getVisitedBuildings() {
        return visitedBuildings;
    }

    public boolean getIsInstructor() {
        return isInstructor;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setClass1(String className) {
        this.class1 = className;
    }

    public void setClass2(String className) {
        this.class2 = className;
    }

    public void setClass3(String className) {
        this.class3 = className;
    }

    public void setClass4(String className) {
        this.class4 = className;
    }

    public void setIsInstructor(boolean bool) {
        isInstructor = bool;
    }

    public void setCovidStatus(CovidStatus covidStatus) {
        this.covidStatus = covidStatus;
    }

}
