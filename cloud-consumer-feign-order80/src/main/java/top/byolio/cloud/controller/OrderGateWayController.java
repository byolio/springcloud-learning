package top.byolio.cloud.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.byolio.cloud.apis.PayFeignApi;
import top.byolio.cloud.resp.ResultData;

import java.util.Enumeration;

/**
 * @ClassName: OrderGateWayController
 * @Description:
 * @Author Byolio
 * @Create 2025/11/11 10:45
 * @Version 1.0
 */
@RestController
@RequestMapping("feign/pay")
public class OrderGateWayController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("gateway/get/{id}")
    public ResultData myGateway(@PathVariable("id") Integer id){
        return payFeignApi.getInfoByGatewayId(id);
    }

    @GetMapping("gateway/get/info")
    public ResultData getGateWayInfo(){
        return payFeignApi.getInfoByGateway();
    }

}
