package com.zhouzhi.wangyue.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.Serializable;

public class WxAccessToken implements Serializable {

    private static final long serialVersionUID = -6962050268850188578L;
    private String accessToken;

    private int expiresIn = -1;

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


    public WxAccessToken() {
    }

    public WxAccessToken(String accessToken, int expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    public static WxAccessToken fromJson(String json) {
        WxAccessToken token = JSON.parseObject(json, new TypeReference<WxAccessToken>() {
        });
        return token;
    }

}
