package com.atguigu.spring.cloud.api;

import com.atguigu.spring.cloud.entity.Employee;
import com.atguigu.spring.cloud.factory.MyFallbackFactory;
import com.atguigu.spring.cloud.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// 表示当前接口和一个provider对应，value属性指定要对应的provider微服务名称
//  fallbackFactory：指定provider不可用时提供备用方案的工厂类型
@FeignClient(value = "atguigu-provider",fallbackFactory = MyFallbackFactory.class)
public interface EmployeeRemoteService {
    //    远程调用的接口方法
//    要求：
//    1.@RequestMapping映射的地址一致
//    2.方法声明一致
//    3.@requestParam、@PathVariable。。不能省略，两边保持一致
    @RequestMapping("/provider/get/employee/remote")
    public Employee getEmployeeRemote();

    @RequestMapping("/provider/get/emp/list/remote")
    List<Employee> getEmployeeListRemote(@RequestParam("keyword") String keyword);

    @RequestMapping("/provider/get/emp/with/circuit/breaker")
    public ResultEntity<Employee> getEmpWithCircuitBreaker(@RequestParam("signal") String signal);
}
