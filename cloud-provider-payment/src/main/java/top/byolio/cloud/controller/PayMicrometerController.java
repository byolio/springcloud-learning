package top.byolio.cloud.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.byolio.cloud.resp.ResultData;

/**
 * @ClassName: PayMicrometerController
 * @Description:
 * @Author Byolio
 * @Create 2025/11/10 9:09
 * @Version 1.0
 */
@RestController
@RequestMapping("pay/micrometer")
public class PayMicrometerController {
    @GetMapping("get/{id}")
    public ResultData myMicrometer(@PathVariable("id") Integer id) {
        if(id < 0) throw new RuntimeException("id不能为负数");
        return ResultData.success("hello, micrometer! inputId: "
                + id + IdUtil.simpleUUID());
    }
}
