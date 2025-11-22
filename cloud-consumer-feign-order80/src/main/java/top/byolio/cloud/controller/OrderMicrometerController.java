package top.byolio.cloud.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.byolio.cloud.apis.PayFeignApi;
import top.byolio.cloud.resp.ResultData;

/**
 * @ClassName: OrderMicrometerController
 * @Description:
 * @Author Byolio
 * @Create 2025/11/10 9:19
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("feign/pay")
public class OrderMicrometerController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping(value = "micrometer/get/{id}")
    public ResultData myMicrometer(@PathVariable("id") Integer id) {
        return payFeignApi.getInfoByMicrometer(id);
    }
}
