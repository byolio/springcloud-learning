package top.byolio.cloud.apis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.byolio.cloud.resp.ResultData;

/**
 * @ClassName: PayFeignSentinelApi
 * @Description:
 * @Author Byolio
 * @Create 2025/11/19 20:36
 * @Version 1.0
 */
@FeignClient(value = "nacos-payment-provider", fallback = PayFeignSentinelApiFallBack.class, path = "/pay/nacos")
public interface PayFeignSentinelApi {
    @GetMapping(value = "/{orderNo}")
    ResultData getPaymentByOrderNo(@PathVariable("orderNo") String orderNo);
}
