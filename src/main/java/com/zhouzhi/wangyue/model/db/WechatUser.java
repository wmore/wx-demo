package com.zhouzhi.wangyue.model.db;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import javax.persistence.*;
import java.util.Arrays;


@Entity
//指定表名，指定唯一约束
@Table(name = "t_wechat_user", uniqueConstraints = {@UniqueConstraint(columnNames = "openId")})
public class WechatUser {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String nickName;
    private String openId;
    private boolean subscribe;
    private int sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headImgUrl;
    private String subscribeTime;
    private String remark;
    private int groupId;
    private String[] tagIdList;
    private String subscribeScene;
    private String qrScene;
    private String qrSceneStr;

    public WechatUser() {
    }

    public WechatUser(String nickName, String openId,  boolean subscribe, int sex, String language, String city, String province, String country, String headImgUrl, String subscribeTime, String remark, int groupId, String[] tagIdList, String subscribeScene, String qrScene, String qrSceneStr) {
        this.nickName = nickName;
        this.openId = openId;
        this.subscribe = subscribe;
        this.sex = sex;
        this.language = language;
        this.city = city;
        this.province = province;
        this.country = country;
        this.headImgUrl = headImgUrl;
        this.subscribeTime = subscribeTime;
        this.remark = remark;
        this.groupId = groupId;
        this.tagIdList = tagIdList;
        this.subscribeScene = subscribeScene;
        this.qrScene = qrScene;
        this.qrSceneStr = qrSceneStr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }


    public boolean isSubscribe() {
        return subscribe;
    }

    public void setSubscribe(boolean subscribe) {
        this.subscribe = subscribe;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(String subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String[] getTagIdList() {
        return tagIdList;
    }

    public void setTagIdList(String[] tagIdList) {
        this.tagIdList = tagIdList;
    }

    public String getSubscribeScene() {
        return subscribeScene;
    }

    public void setSubscribeScene(String subscribeScene) {
        this.subscribeScene = subscribeScene;
    }

    public String getQrScene() {
        return qrScene;
    }

    public void setQrScene(String qrScene) {
        this.qrScene = qrScene;
    }

    public String getQrSceneStr() {
        return qrSceneStr;
    }

    public void setQrSceneStr(String qrSceneStr) {
        this.qrSceneStr = qrSceneStr;
    }

    public static WechatUser fromJson(String json) {
        WechatUser user = JSON.parseObject(json, new TypeReference<WechatUser>() {
        });
        return user;
    }

    @Override
    public String toString() {
        return "WechatUser{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", openId='" + openId + '\'' +
                ", subscribe=" + subscribe +
                ", sex=" + sex +
                ", language='" + language + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", subscribeTime=" + subscribeTime +
                ", remark='" + remark + '\'' +
                ", groupId=" + groupId +
                ", tagIdList=" + Arrays.toString(tagIdList) +
                ", subscribeScene='" + subscribeScene + '\'' +
                ", qrScene='" + qrScene + '\'' +
                ", qrSceneStr='" + qrSceneStr + '\'' +
                '}';
    }
}
