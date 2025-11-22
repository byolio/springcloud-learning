package top.byolio.cloud.controller;

import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import top.byolio.cloud.entities.PayDTO;
import top.byolio.cloud.resp.ResultData;

import java.util.List;

/**
 * ClassName: RestController
 * Description:
 *
 * @Author Byolio
 * @Create 2025/10/24 20:03
 * @Version 1.0
 */
@RestController
@RequestMapping("consumer/pay/")
public class OrderController {

//    public static final String PaymentSrv_URL = "http://localhost:8001/pay";
    public static final String PaymentSrv_URL = "http://cloud-payment-service/pay";
    // 使用consul上注册中心的name

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("add")
    public ResultData addOrder(@RequestBody PayDTO payDTO){
        return restTemplate.postForObject(PaymentSrv_URL + "/add", payDTO, ResultData.class);
    }

    @GetMapping("get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PaymentSrv_URL + "/get/" + id, ResultData.class, id);
    }

    @GetMapping("get")
    public ResultData getPayInfos(){
        return restTemplate.getForObject(PaymentSrv_URL + "/get", ResultData.class);
    }

    @GetMapping("get/info")
    public String getInfo(){
        return restTemplate.getForObject(PaymentSrv_URL + "/get/info", String.class);
    }

    @DeleteMapping("del/{id}")
    public ResultData delOrder(@PathVariable("id") Integer id){
        // 使用 exchange 方法发送 DELETE 请求，并获取返回的 ResultData
        return restTemplate.exchange(
                PaymentSrv_URL + "/del/" + id,
                HttpMethod.DELETE, // 明确指定 HTTP DELETE 方法
                null,              // RequestEntity，删除操作通常不需要请求体
                ResultData.class   // 期望的返回类型
        ).getBody();             // 获取响应体
    }

    @PutMapping("update")
    public ResultData updateOrder(@RequestBody PayDTO payDTO){
        // 使用 exchange 发送 PUT 请求，携带请求体，返回 ResultData
        return restTemplate.exchange(
                PaymentSrv_URL + "/update",   // 请求路径
                HttpMethod.PUT,                // 指定 PUT 方法
                new org.springframework.http.HttpEntity<>(payDTO), // 携带请求体
                ResultData.class               // 返回类型
        ).getBody();                            // 获取响应体
    }

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/discovery")
    public String discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        System.out.println("=======================");
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort());
        }
        return instances.get(0).getServiceId() + ":" + instances.get(0).getPort();
    }
}
