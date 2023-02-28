package com.atguigu.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// 启用feign客户端功能
@EnableFeignClients
@SpringBootApplication
public class AtguiguMainClass {
    public static void main(String args[]){
        SpringApplication.run(AtguiguMainClass.class,args);
    }
}
