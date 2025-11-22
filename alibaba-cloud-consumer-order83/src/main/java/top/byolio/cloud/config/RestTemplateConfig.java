package top.byolio.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: RestTemplateConfig
 * @Description:
 * @Author Byolio
 * @Create 2025/11/15 19:46
 * @Version 1.0
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    @LoadBalanced   // 赋予负载均衡的能力
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
