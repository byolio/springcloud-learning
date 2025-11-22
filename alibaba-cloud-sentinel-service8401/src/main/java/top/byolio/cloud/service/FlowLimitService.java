package top.byolio.cloud.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @ClassName: FlowLimitService
 * @Description:
 * @Author Byolio
 * @Create 2025/11/17 18:44
 * @Version 1.0
 */
@Service
public class FlowLimitService {

    @SentinelResource(value = "common")
    public void common(){
        System.out.println("----FlowLimitService come in common");
    }

}
