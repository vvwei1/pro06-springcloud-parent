package com.atguigu.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

//启用断路器功能
@EnableHystrix
@SpringBootApplication
public class AtguiguMainClass {
    public static void main(String args[]){
        SpringApplication.run(AtguiguMainClass.class,args);
    }
}
