package com.atguigu.spring.cloud.factory;

import com.atguigu.spring.cloud.api.EmployeeRemoteService;
import com.atguigu.spring.cloud.entity.Employee;
import com.atguigu.spring.cloud.util.ResultEntity;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 实现Consumer端服务降级功能
 * 实现 FallbackFactory 接口时要传入 @FeignClient 注解标记的接口类型
 * 在 create 方法中返回 @FeignClient 注解标记的接口类型对象，当Provider调用失败后。会执行该对象对应方法
 * 这个类必须标注@Component注解加入ioc容器
 */
@Component
public class MyFallbackFactory implements FallbackFactory<EmployeeRemoteService> {

    @Override
    public EmployeeRemoteService create(Throwable cause) {
        return new EmployeeRemoteService() {
            @Override
            public Employee getEmployeeRemote() {
                return null;
            }

            @Override
            public List<Employee> getEmployeeListRemote(String keyword) {
                return null;
            }

            @Override
            public ResultEntity<Employee> getEmpWithCircuitBreaker(String signal) {
                return ResultEntity.failed(cause.getMessage()+"降级机制生效");
            }
        };
    }
}