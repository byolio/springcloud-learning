package top.byolio.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: NacosConfigClientController
 * @Description:
 * @Author Byolio
 * @Create 2025/11/16 17:52
 * @Version 1.0
 */
@RestController
@RefreshScope  // 支持nacos动态刷新功能
public class NacosConfigClientController {
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String configInfo() {
        return configInfo;
    }
}
