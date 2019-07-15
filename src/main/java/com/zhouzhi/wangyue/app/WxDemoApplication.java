package com.zhouzhi.wangyue.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.zhouzhi.wangyue"})
public class WxDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxDemoApplication.class, args);
    }
}