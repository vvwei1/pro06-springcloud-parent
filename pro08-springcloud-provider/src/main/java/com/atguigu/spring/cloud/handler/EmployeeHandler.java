package com.atguigu.spring.cloud.handler;

import com.atguigu.spring.cloud.entity.Employee;
import com.atguigu.spring.cloud.util.ResultEntity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

// 微服务要求都返回json数据
@RestController
public class EmployeeHandler {
//    @RequestMapping("/provider/get/employee/remote")
//    public Employee getEmployeeRemote(HttpServletRequest request) {
////        获取当前服务的端口号
//        int serverPort = request.getServerPort();
//        return new Employee(555, "tom555"+" "+serverPort, 555.55);
//    }

    private Logger logger= LoggerFactory.getLogger(EmployeeHandler.class);

        @RequestMapping("/provider/get/employee/remote")
        public Employee getEmployeeRemote() {
        //        获取当前服务的端口号
//            int serverPort = request.getServerPort();
            return new Employee(555, "tom555", 555.55);
        }
        @RequestMapping("/provider/get/emp/list/remote")
        List<Employee> getEmployeeListRemote(@RequestParam("keyword") String keyword){
                logger.info("keyword"+keyword);

            ArrayList<Employee> list = new ArrayList<>();
            list.add(new Employee(4, "wowo", 3434.3));
            return list;
        };

        // 指定当前方法出问题时调用的备份方法
        @HystrixCommand(fallbackMethod = "getEmpWithCircuitBreakerBackup")
        @RequestMapping("/provider/get/emp/with/circuit/breaker")
        public ResultEntity<Employee> getEmpWithCircuitBreaker(@RequestParam("signal") String signal) throws InterruptedException {

            if ("quick-bang".equals(signal)) {
                throw new RuntimeException();
            }
            if ("slow-bang".equals(signal)) {
                TimeUnit.MILLISECONDS.sleep(5000);
            }
            return ResultEntity.successWithData(new Employee(666,"empName666", 666.66));
        }

        public ResultEntity<Employee> getEmpWithCircuitBreakerBackup(@RequestParam("signal") String signal) {
            return ResultEntity.failed("circuit break workded,with signal="+signal);
        }

}
