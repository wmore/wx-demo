package com.zhouzhi.wangyue.model;

import javax.persistence.*;
import java.util.Date;


@Entity
//指定表名，指定唯一约束
@Table(name = "t_wechat_user")
public class WechatUser {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String userNickName;
    private boolean isDeleted;
    private Date createTime;
    private Date updateTime;

}
