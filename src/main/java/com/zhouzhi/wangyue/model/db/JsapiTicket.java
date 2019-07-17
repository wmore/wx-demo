package com.zhouzhi.wangyue.model.db;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "t_jsapi_ticket")
public class JsapiTicket {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String ticket;
    private Date accessTime;
    private int expiresIn = -1; // 秒

    public JsapiTicket() {
    }

    public JsapiTicket(String ticket, Date accessTime, int expiresIn) {
        this.ticket = ticket;
        this.accessTime = accessTime;
        this.expiresIn = expiresIn;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
