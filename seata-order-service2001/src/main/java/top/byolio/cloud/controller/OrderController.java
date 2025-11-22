package top.byolio.cloud.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.byolio.cloud.entities.Order;
import top.byolio.cloud.resp.ResultData;
import top.byolio.cloud.service.OrderService;

/**
 * @ClassName: OrderController
 * @Description:
 * @Author Byolio
 * @Create 2025/11/21 23:39
 * @Version 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/create")
    public ResultData create(Order order) {
        orderService.create(order);
        return ResultData.success(order);
    }

}
