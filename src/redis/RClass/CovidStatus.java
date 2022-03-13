package redis.RClass;

import java.io.Serializable;
import java.util.Date;

public class CovidStatus implements Serializable {

    private char status; // 0: Healthy, 1: Symptomatic, 2: Infected
    private Date date;

    public CovidStatus(char status, Date date) {
        this.status = status;
        this.date = date;
    }

    public char getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
