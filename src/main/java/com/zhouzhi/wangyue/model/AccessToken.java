package com.zhouzhi.wangyue.model;

import javax.persistence.*;
import java.util.Date;

@Entity
//指定表名，指定唯一约束
@Table(name = "t_access_token")
public class AccessToken {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String access_token;
    private long expires_in;
    private Date access_time;

    public AccessToken() {
    }

    public AccessToken(String access_token, long expires_in, Date access_time) {
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.access_time = access_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    public Date getAccess_time() {
        return access_time;
    }

    public void setAccess_time(Date access_time) {
        this.access_time = access_time;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "id=" + id +
                ", access_token='" + access_token + '\'' +
                ", expires_in='" + expires_in + '\'' +
                ", access_time=" + access_time +
                '}';
    }
}
