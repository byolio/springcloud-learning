package top.byolio.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: RateLimitController
 * @Description:
 * @Author Byolio
 * @Create 2025/11/18 14:20
 * @Version 1.0
 */
@RestController
@Slf4j
public class RateLimitController {

    @GetMapping("/rateLimit/byUrl")
    public String byUrl(){
        return "rest 限流测试";
    }

    @GetMapping("rateLimit/byResource/get/{id}")
    @SentinelResource(value = "bySentinelResource", blockHandler = "handlerBlockHandler", fallback = "fallbackAction")
    public String byResource(@PathVariable("id") Integer id){
        if(id == 0) throw new RuntimeException("id等于0的异常");
        return "按照资源名称做bySentinelResource限流测试";
    }

    // 限流时触发
    public String handlerBlockHandler(Integer id, BlockException e){
        return "bySentinelResource服务不可用";
    }

    // 逻辑异常throw时触发
    public String fallbackAction(Integer id, Throwable e){
        return "程序逻辑异常了";
    }

    // 热点限流
    @GetMapping("/testHotKey")
    @SentinelResource(value = "sentinelHotKey", blockHandler = "hotKeyBlockHandler")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2){
        return "testHotKey热点限流测试";
    }

    // 热点限流时触发
    public String hotKeyBlockHandler(String p1, String p2, BlockException e){
        return "testHotKey服务不可用";
    }

}
