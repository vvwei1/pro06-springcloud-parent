package com.atguigu.spring.cloud.handler;

import com.atguigu.spring.cloud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HumanResourceHandler {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/get/employee")
    public Employee getEmployeeRemote() {
        // 将远程微服务调用地址从ip地址+端口号改为微服务名
        String host = "http://ATGUIGU-PROVIDER";
        String url = "/provider/get/employee/remote";
        // 3. 通过RestTemplate调用远程微服务
//        参数2：返回值类型
        return restTemplate.getForObject(host+url, Employee.class);
    }
}
