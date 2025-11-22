package top.byolio.cloud.controller;

import cn.hutool.core.date.DateUtil;
import io.swagger.v3.oas.models.security.SecurityScheme;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import top.byolio.cloud.apis.PayFeignApi;
import top.byolio.cloud.entities.PayDTO;
import top.byolio.cloud.resp.ResultData;
import top.byolio.cloud.resp.ReturnCodeEnum;

import java.util.List;

/**
 * ClassName: RestController
 * Description:
 *
 * @Author Byolio
 * @Create 2025/10/24 20:03
 * @Version 1.0
 */
// openFeign 默认等待60s, 超时后报错
@RestController
@RequestMapping("feign/pay")
public class OrderController {
    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping("add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        ResultData resultData = payFeignApi.addPay(payDTO);
        return resultData;
    }

    @GetMapping("get/{id}")
    public ResultData getOrderInfo(@PathVariable("id") Integer id) {
        ResultData resultData = null;
        try {
            System.out.println("开始时间: " + DateUtil.now() + " orderController.getOrderInfo");
            resultData = payFeignApi.getPayInfo(id);
        } catch (Exception e) {
            System.out.println("结束时间: " + DateUtil.now());
            e.printStackTrace();
            resultData = ResultData.fail(ReturnCodeEnum.RC500, e.getMessage());
        }
        return resultData;
    }

    @GetMapping("get/info")
    public String getMyLB() {
        String info = payFeignApi.myLB();
        return info;
    }

}
