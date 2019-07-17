package com.zhouzhi.wangyue.controller.rest;

import com.zhouzhi.wangyue.util.TemplateMsgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {


    @Value("${com.constant.weixin.token}")
    public String token;//自己在微信测试平台设置的token
    @Value("${com.constant.weixin.appId}")
    public String appId;//自己在微信测试平台设置的appId
    @Value("${com.constant.weixin.secret}")
    public String secret;//自己在微信测试平台设置的secret

    @Autowired
    private TemplateMsgUtils templateMsgUtils;


    @RequestMapping("/send_all")
    public String sendMsgToAllUsers() {
        templateMsgUtils.sendMsgToAllUsers();
        return "OK";
    }

}
