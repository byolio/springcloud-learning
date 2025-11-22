package top.byolio.cloud.apis;

import org.springframework.stereotype.Component;
import top.byolio.cloud.resp.ResultData;
import top.byolio.cloud.resp.ReturnCodeEnum;

/**
 * @ClassName: PayFeignSentinelApiFallBack
 * @Description:
 * @Author Byolio
 * @Create 2025/11/19 20:41
 * @Version 1.0
 */
@Component
public class PayFeignSentinelApiFallBack implements PayFeignSentinelApi {
    @Override
    public ResultData getPaymentByOrderNo(String orderNo) {
        return ResultData.fail(ReturnCodeEnum.RC500, "feign fallback 服务降级");
    }
}
