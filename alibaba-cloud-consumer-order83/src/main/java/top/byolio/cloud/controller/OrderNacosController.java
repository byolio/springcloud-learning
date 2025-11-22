package top.byolio.cloud.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import top.byolio.cloud.apis.PayFeignSentinelApi;
import top.byolio.cloud.resp.ResultData;

/**
 * @ClassName: OrderNacosController
 * @Description:
 * @Author Byolio
 * @Create 2025/11/15 19:48
 * @Version 1.0
 */
@RestController
@RequestMapping("consumer/pay/nacos")
public class OrderNacosController {
    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    @GetMapping("get/{id}")
    public String getPaymentInfo(@PathVariable("id") Integer id){
        String result = restTemplate.getForObject(serverUrl+"/get/{id}", String.class, id);
        return result + "  with consumer running";
    }

    @Resource
    private PayFeignSentinelApi payFeignSentinelApi;

    @GetMapping("/{orderNo}")
    public ResultData getInfoByOrderNo(@PathVariable("orderNo") String orderNo){
        return payFeignSentinelApi.getPaymentByOrderNo(orderNo);
    }

}
