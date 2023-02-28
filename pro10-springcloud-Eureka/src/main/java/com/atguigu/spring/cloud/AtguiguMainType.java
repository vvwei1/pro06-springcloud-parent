package com.atguigu.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer //启用Eureka服务端
@SpringBootApplication
public class AtguiguMainType {
    public static void main(String args[]){
        SpringApplication.run(AtguiguMainType.class,args);
    }
}
