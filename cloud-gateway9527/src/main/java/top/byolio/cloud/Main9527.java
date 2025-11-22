package top.byolio.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName: Main9527
 * @Description:
 * @Author Byolio
 * @Create 2025/11/11 8:37
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient    // 服务注册和发现
public class Main9527 {
    public static void main(String[] args) {
        SpringApplication.run(Main9527.class, args);
    }
}
