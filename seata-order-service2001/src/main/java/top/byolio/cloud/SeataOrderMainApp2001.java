package top.byolio.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ClassName: SeataOrderMainApp2001
 * @Description:
 * @Author Byolio
 * @Create 2025/11/21 21:47
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("top.byolio.cloud.mapper")   // tk.mybatis的
@EnableFeignClients
public class SeataOrderMainApp2001 {
    public static void main(String[] args) {
        SpringApplication.run(SeataOrderMainApp2001.class,args);
    }
}
