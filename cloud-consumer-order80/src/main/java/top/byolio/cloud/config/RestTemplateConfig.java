package top.byolio.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName: RestTemplateConfig
 * Description:
 *
 * @Author Byolio
 * @Create 2025/10/24 20:01
 * @Version 1.0
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced  // 添加对于微服务负载均衡的标签
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
