package top.byolio.cloud.mygateway;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * @ClassName: MyRoutePredicateFactory
 * @Description:  自定义路由器
 * @Author Byolio
 * @Create 2025/11/11 20:39
 * @Version 1.0
 */
@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {

    public static final String MY_KEY = "userType";

    public MyRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList(MY_KEY);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                String userType = serverWebExchange.getRequest().getQueryParams().getFirst(MY_KEY);
                if (userType == null) {
                    return false;
                }
                // 如果参数处在就开始比较
                if (userType.equalsIgnoreCase(config.getUserType())) {
                    return true;
                }
                return false;
            }

            @Override
            public Object getConfig() {
                return config;
            }

            @Override
            public String toString() {
                return "MyRoutePredicateFactory: " + config.getUserType();
            }
        };
    }

    @Validated
    public static class Config {
        @Setter@Getter@NotEmpty
        private String userType;   // 钻/金/银 配置会员等级
    }
}
