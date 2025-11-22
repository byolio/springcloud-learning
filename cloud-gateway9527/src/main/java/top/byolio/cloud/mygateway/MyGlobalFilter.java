package top.byolio.cloud.mygateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ClassName: MyGlobalFilter
 * @Description:  自定义全局过滤器
 * @Author Byolio
 * @Create 2025/11/12 18:55
 * @Version 1.0
 */
@Component
@Slf4j
public class MyGlobalFilter implements GlobalFilter, Ordered {

    public static final String BEGIN_VISIT_TIME = "beginVisitTime"; // 方法开始调用时间

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 先记录下访问接口的开始时间
        exchange.getAttributes().put(BEGIN_VISIT_TIME, System.currentTimeMillis());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long beginVisitTime = exchange.getAttribute(BEGIN_VISIT_TIME);
            if (beginVisitTime != null) {
                log.info("访问接口主机: " + exchange.getRequest().getURI().getHost());
                log.info("接口调用时间: " + (System.currentTimeMillis() - beginVisitTime) + "毫秒");
                log.info("=========================分割线=========================");
            }
        }));
    }

    /**
     * 数字越小, 优先级越高
     * @return 优先级
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
