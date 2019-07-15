package com.zhouzhi.wangyue.controller.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class HelloRestController {

    @RequestMapping("/hello")
    public String hello () {
        return "Hello world idea!";
    }

    @Value("${com.constant.weixin.token}")
    public String token;//自己在微信测试平台设置的token



    @RequestMapping("/token")
    public String token () {
        return token;
    }
}
