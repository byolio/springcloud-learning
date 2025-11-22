package top.byolio.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName: MainFeign80
 * Description:
 *
 * @Author Byolio
 * @Create 2025/10/28 10:18
 * @Version 1.0
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "top.byolio.cloud.apis")
@EnableDiscoveryClient
public class MainFeign80 {
    public static void main(String[] args) {
        SpringApplication.run(MainFeign80.class, args);
    }
}
