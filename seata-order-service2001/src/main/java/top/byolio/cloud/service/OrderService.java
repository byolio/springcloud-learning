package top.byolio.cloud.service;

import top.byolio.cloud.entities.Order;

/**
 * @ClassName: OrderService
 * @Description:
 * @Author Byolio
 * @Create 2025/11/21 21:54
 * @Version 1.0
 */
public interface OrderService {
    /**
     * 创建订单
     * @param order
     */
    void create(Order order);
}
