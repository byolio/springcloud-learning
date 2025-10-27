package top.byolio.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * ClassName: Main
 * Description:
 *
 * @Author Byolio
 * @Create 2025/10/20 16:47
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("top.byolio.cloud.mapper")   // 自动启动类
@EnableDiscoveryClient
@RefreshScope   // 动态及时刷新
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
