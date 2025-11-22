package top.byolio.cloud.service.impl;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import top.byolio.cloud.apis.AccountFeignApi;
import top.byolio.cloud.apis.StorageFeignApi;
import top.byolio.cloud.entities.Order;
import top.byolio.cloud.mapper.OrderMapper;
import top.byolio.cloud.service.OrderService;

/**
 * @ClassName: OrderServiceImpl
 * @Description:
 * @Author Byolio
 * @Create 2025/11/21 21:55
 * @Version 1.0
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    // 订单微服务通过openFeign调用库存和账户微服务
    @Resource
    private StorageFeignApi storageFeignApi;

    @Resource
    private AccountFeignApi accountFeignApi;

    // 添加GlobalTransactional为TM
    @Override
    @GlobalTransactional(name = "byolio-create-order", rollbackFor = Exception.class)  // 开启AT模式
    public void create(Order order) {
        // XID : 全局事务id的检查, 重要
        String xid = RootContext.getXID();
        // 1. 新建订单
        log.info("----------------------开始新建订单: " + "\t" + "xid: " + xid);
        order.setStatus(0);  // 创建中, 初始化赋值为0
        int result = orderMapper.insertSelective(order);  // 插入order
        Order orderFromDB = null;
        if(result > 0){
            orderFromDB = orderMapper.selectOne(order);  // 查询相似的order
            System.out.println();
            log.info("-----> 新建订单成功, orderFromDB info : " + orderFromDB);
            System.out.println();
            log.info("-----> 调用storage库存, 做库存count--");
            storageFeignApi.decrease(orderFromDB.getProductId(), orderFromDB.getCount());
            System.out.println();
            log.info("-----> 调用storage库存完成");
            System.out.println();
            log.info("-----> 调用account库存, 做money--");
            // money为order总价, 不是单价
            accountFeignApi.decrease(orderFromDB.getUserId(), orderFromDB.getMoney());
            System.out.println();
            log.info("-----> 调用account库存完成");
            // 修改订单状态
            log.info("-----> 修改订单状态");
            orderFromDB.setStatus(1);

            Example whereCondition = new Example(Order.class);
            Example.Criteria criteria = whereCondition.createCriteria();
            criteria.andEqualTo("userId", orderFromDB.getUserId());
            criteria.andEqualTo("status", 0);

            int updateResult = orderMapper.updateByExampleSelective(orderFromDB, whereCondition);
            log.info("--->修改订单状态完成, updateResult: " + updateResult);
        }
        System.out.println();
        log.info("----------------------结束新建订单: " + "\t" + "xid: " + xid);
    }
}
