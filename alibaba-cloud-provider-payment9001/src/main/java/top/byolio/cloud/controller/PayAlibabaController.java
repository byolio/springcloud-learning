package top.byolio.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.byolio.cloud.entities.PayDTO;
import top.byolio.cloud.resp.ResultData;
import top.byolio.cloud.resp.ReturnCodeEnum;

import java.math.BigDecimal;

/**
 * @ClassName: PayAlibabaController
 * @Description:
 * @Author Byolio
 * @Create 2025/11/14 22:05
 * @Version 1.0
 */
@RestController
@RequestMapping("/pay/nacos")
public class PayAlibabaController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/get/{id}")
    public String getPayInfo(@PathVariable("id") String id){
        return "nacos registry, serverPort: " + serverPort + "\t id: " + id;
    }

    @GetMapping("/{orderNo}")
    @SentinelResource(value = "getPayByOrderNo", blockHandler = "orderNoBlockHandler")
    public ResultData getPayByOrderNo(@PathVariable("orderNo") String orderNo){
        PayDTO payDTO = new PayDTO();
        // 模拟从数据库查询数据的返回值
        payDTO.setId(1024);
        payDTO.setOrderNo(orderNo);
        payDTO.setAmount(BigDecimal.valueOf(Math.random()));
        payDTO.setPayNo("pay: " + IdUtil.fastUUID());
        payDTO.setUserId(1);
        return ResultData.success(payDTO);
    }

    public ResultData orderNoBlockHandler(String orderNo, BlockException blockException){
        return ResultData.fail(ReturnCodeEnum.RC500, "服务不可用, " + "触发了sentinel的流控配置规则");
    }

}
