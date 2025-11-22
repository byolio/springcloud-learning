package top.byolio.cloud.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.byolio.cloud.service.FlowLimitService;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: FlowLimitConfig
 * @Description:
 * @Author Byolio
 * @Create 2025/11/17 13:05
 * @Version 1.0
 */
@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA(){
        return "testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "testB";
    }

    @Resource
    private FlowLimitService flowLimitService;

    @GetMapping("/testC")
    public String testC(){
        flowLimitService.common();
        return "testC";
    }
    @GetMapping("/testD")
    public String testD(){
        flowLimitService.common();
        return "testD";
    }

    @GetMapping("/testE")
    public String testE(){
        System.out.println(System.currentTimeMillis() + " 排队等待");
        return "testE";
    }

    /**
     * 慢调用熔断
     * @return
     */
    @GetMapping("/testF")
    public String testF(){
        // 触发慢调用
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "testF 慢调用";
    }

    /**
     * 异常比例和异常数熔断
     * @return
     */
    @GetMapping("/testG")
    public String testG(){
        int a = 10/0;
        return "testG";
    }
}
