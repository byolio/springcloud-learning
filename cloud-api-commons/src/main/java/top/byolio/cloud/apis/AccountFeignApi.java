package top.byolio.cloud.apis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.byolio.cloud.resp.ResultData;

/**
 * @ClassName: AccountFeignApi
 * @Description:
 * @Author Byolio
 * @Create 2025/11/21 16:25
 * @Version 1.0
 */
@FeignClient(value = "seata-account-service", path = "/account")
public interface AccountFeignApi {
    /**
     * 账户余额扣减
     * @param userId
     * @param money
     * @return
     */
    @PostMapping("/decrease")
    ResultData decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money);
}
