package top.byolio.cloud.controller;

import cn.hutool.core.util.IdUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.byolio.cloud.apis.PayFeignApi;
import top.byolio.cloud.entities.Pay;
import top.byolio.cloud.entities.PayDTO;
import top.byolio.cloud.resp.ResultData;
import top.byolio.cloud.service.PayService;

import java.util.Enumeration;

/**
 * @ClassName: PayGateWayController
 * @Description:
 * @Author Byolio
 * @Create 2025/11/11 10:13
 * @Version 1.0
 */
@RestController
@RequestMapping("pay/gateway")
public class PayGateWayController {
    @Resource
    private PayService payService;

    @GetMapping("get/{id}")
    public ResultData myGateWay(@PathVariable("id") Integer id){
        if(id < 0) throw new RuntimeException("id不能为负数");
        Pay pay = payService.getById(id);
        PayDTO payDTO = new PayDTO();
        BeanUtils.copyProperties(pay,payDTO);
        return ResultData.success(payDTO);          // spring restful
    }

    @GetMapping("get/info")
    public ResultData getGateWayInfo(){
        return ResultData.success("hello, gateway! inputId: " +
                IdUtil.simpleUUID());
    }

    @GetMapping("filter")
    public ResultData getGateWayFilter(HttpServletRequest request){
        String result = "";
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headName = headers.nextElement();
            String headValue = request.getHeader(headName);
            System.out.println("请求头名: " + headName + "\t\t\t请求头值: " + headValue);
            if(headName.equalsIgnoreCase("X-Request-byolio1")
                    || headName.equalsIgnoreCase("X-Request-byolio2")){
                result = result + headName + "\t" + headValue + " ";
            }
        }
        System.out.println("=================================");
        String customerId = request.getParameter("customerId");
        System.out.println("customerId = " + customerId);

        String customerName = request.getParameter("customerName");
        System.out.println("customerName = " + customerName);
        System.out.println("=================================");

        return ResultData.success(result);
    }


}
