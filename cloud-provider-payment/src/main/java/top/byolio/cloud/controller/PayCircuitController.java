package top.byolio.cloud.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.byolio.cloud.resp.ResultData;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: PayCircuitController
 * Description:
 *
 * @Author Byolio
 * @Create 2025/10/30 8:04
 * @Version 1.0
 */
@RestController
@RequestMapping("pay")
public class PayCircuitController {

    // 熔断器是代码可用性低和堵塞报错
    @GetMapping(value = "/circuit/get/{id}")
    public ResultData<String> myCircuit(@PathVariable("id") Integer id) {
        if(id < 0) throw new RuntimeException("id不能为负数");
        // 堵塞看熔断效果
        try {
            // count_base测试
//            if(id == 3) TimeUnit.SECONDS.sleep(60);
            // time_base测试, 慢调用测试
            if(id == 3) TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResultData.success("hello, circuit! inputId: " + id + IdUtil.simpleUUID());
    }

    // 测试舱壁隔离, 限制线程进入数量
    @GetMapping(value = "bulkhead/get/{id}")
    public ResultData<String> myBulkhead(@PathVariable("id") Integer id) {
        if(id < 0) throw new RuntimeException("id不能为负数");
        try {
            if(id == 3) TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResultData.success("hello, bulkhead! inputId: " + id + IdUtil.simpleUUID());
    }

    // 测试限制器
    @GetMapping(value = "ratelimit/get/{id}")
    public ResultData<String> myRatelimit(@PathVariable("id") Integer id) {
        if(id < 0) throw new RuntimeException("id不能为负数");
        try {
            if(id == 3) TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResultData.success("hello, ratelimit! inputId: " + id + IdUtil.simpleUUID());
    }

}
