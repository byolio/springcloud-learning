package top.byolio.cloud.apis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import top.byolio.cloud.entities.PayDTO;
import top.byolio.cloud.resp.ResultData;

/**
 * ClassName: PayFeignApi
 * Description:
 *
 * @Author Byolio
 * @Create 2025/10/28 10:36
 * @Version 1.0
 */
//@FeignClient(value = "cloud-payment-service", path = "pay")
@FeignClient(value = "cloud-gateway", path = "pay")
public interface PayFeignApi {

    @PostMapping("/circuit/add")
    ResultData addPay(@RequestBody PayDTO payDTO);

    @GetMapping("/circuit/get/{id}")
    ResultData getPayInfo(@PathVariable("id") Integer id);

    @GetMapping("/circuit/get/info")
    String myLB();

    @GetMapping("bulkhead/get/{id}")
    ResultData getInfoByBulkhead(@PathVariable("id") Integer id);

    @GetMapping("ratelimit/get/{id}")
    ResultData getInfoByRatelimit(@PathVariable("id") Integer id);

    @GetMapping("micrometer/get/{id}")
    ResultData getInfoByMicrometer(@PathVariable("id") Integer id);

    @GetMapping("gateway/get/{id}")
    ResultData getInfoByGatewayId(@PathVariable("id") Integer id);

    @GetMapping("gateway/get/info")
    ResultData getInfoByGateway();
}
