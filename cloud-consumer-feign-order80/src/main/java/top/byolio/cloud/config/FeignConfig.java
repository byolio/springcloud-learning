package top.byolio.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: FeignConfig
 * Description:
 *
 * @Author Byolio
 * @Create 2025/10/28 16:25
 * @Version 1.0
 */
@Configuration
public class FeignConfig {
    @Bean
    public Retryer myRetryer(){
        // 1ms maxPeriod 小于 period 因此退避指数不会生效, 3次重试时间固定
//        return new Retryer.Default(100, 1, 3);
        return Retryer.NEVER_RETRY;
    }


//    开启日志
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
