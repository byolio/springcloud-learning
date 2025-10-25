package top.byolio.cloud.config;

import lombok.extern.slf4j.Slf4j;
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
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
