package com.shenma.demomongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoMongoApplication {

    public static void main(String[] args) {
        System.out.println("启动项目");
        SpringApplication.run(DemoMongoApplication.class, args);
    }
}
