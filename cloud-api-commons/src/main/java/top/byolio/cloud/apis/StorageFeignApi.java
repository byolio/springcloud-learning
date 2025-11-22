package top.byolio.cloud.apis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.byolio.cloud.resp.ResultData;

/**
 * @ClassName: OrderFeignApi
 * @Description:
 * @Author Byolio
 * @Create 2025/11/21 16:24
 * @Version 1.0
 */
@FeignClient(value = "seata-storage-service", path = "/storage")
public interface StorageFeignApi {
    /**
     * 减少库存
     * @return
     */
    @PostMapping("/decrease")
    ResultData decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
