package com.zhouzhi.wangyue.model.db;

import com.zhouzhi.wangyue.model.weixin.WxAccessToken;

import javax.persistence.*;
import java.util.Date;

@Entity
//指定表名，指定唯一约束
@Table(name = "t_access_token")
public class AccessToken {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String accessToken;
    private int expiresIn;// 秒
    private Date accessTime;

    public AccessToken() {
    }

    public AccessToken(String access_token, int expires_in, Date access_time) {
        this.accessToken = access_token;
        this.expiresIn = expires_in;
        this.accessTime = access_time;
    }

    public AccessToken(WxAccessToken wxAccessToken) {
        this.accessToken = wxAccessToken.getAccessToken();
        this.expiresIn = wxAccessToken.getExpiresIn();
        this.accessTime = new Date();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "id=" + id +
                ", accessToken='" + accessToken + '\'' +
                ", expiresIn='" + expiresIn + '\'' +
                ", accessTime=" + accessTime +
                '}';
    }

}
