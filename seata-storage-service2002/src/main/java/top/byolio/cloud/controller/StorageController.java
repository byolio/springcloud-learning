package top.byolio.cloud.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.byolio.cloud.resp.ResultData;
import top.byolio.cloud.service.StorageService;

/**
 * @ClassName: StorageController
 * @Description:
 * @Author Byolio
 * @Create 2025/11/22 16:32
 * @Version 1.0
 */
@RestController
@RequestMapping("/storage")
public class StorageController {
    @Resource
    private StorageService storageService;

    @PostMapping("/decrease")
    public ResultData decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count) {
        storageService.decrease(productId, count);
        return ResultData.success("productId:" + productId + " count:" + count);
    }
}
