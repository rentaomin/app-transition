package com.rtm.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages={"com.rtm.application.mybatisFlex.demo.mapper","com.rtm.application.governance.mybatis.mapper"})
@SpringBootApplication(scanBasePackages = {"com.rtm.application.mybatisFlex","com.rtm.application.governance.mybatis","com.rtm.application.mybatisFlex.page"})
public class AppTransitionApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppTransitionApplication.class);
    }
}
