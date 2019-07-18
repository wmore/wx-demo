package com.zhouzhi.wangyue.app;

import com.zhouzhi.wangyue.websocket.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@ComponentScan({"com.zhouzhi.wangyue"})
@EnableJpaRepositories(basePackages = "com.zhouzhi.wangyue.dao")
@EntityScan(basePackages = "com.zhouzhi.wangyue.model")
public class WxDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxDemoApplication.class, args);
    }
}