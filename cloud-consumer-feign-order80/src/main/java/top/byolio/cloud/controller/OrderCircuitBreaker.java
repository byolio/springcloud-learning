package top.byolio.cloud.controller;

import cn.hutool.core.util.IdUtil;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.byolio.cloud.apis.PayFeignApi;
import top.byolio.cloud.resp.ResultData;
import top.byolio.cloud.resp.ReturnCodeEnum;

import java.util.concurrent.CompletableFuture;

/**
 * ClassName: OrderCircuitBreaker
 * Description:
 *
 * @Author Byolio
 * @Create 2025/10/30 8:41
 * @Version 1.0
 */
@RestController
@RequestMapping("feign/pay")
public class OrderCircuitBreaker {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("circuit/get/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
    public ResultData myCircuitBreaker(@PathVariable("id") Integer id){
        return payFeignApi.getPayInfo(id);
    }

    // 熔断器fallback兜底
    public ResultData myCircuitFallback(Integer id, Throwable e){
        return ResultData.fail(ReturnCodeEnum.RC201, "系统繁忙, 请稍后再试");
    }

    /**
     * 舱壁隔离的semaphore
     */
//    @GetMapping("bulkhead/get/{id}")
//    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myBulkheadFallback", type = Bulkhead.Type.SEMAPHORE)  // 使用信号量
//    public ResultData myBulkhead(@PathVariable("id") Integer id){
//        return payFeignApi.getInfoByBulkhead(id);
//    }
//
//     舱壁隔离的fallback兜底
//    public ResultData myBulkheadFallback(Throwable e){
//        return ResultData.fail(ReturnCodeEnum.RC201, "隔板超出最大限制, 系统繁忙, 请稍后再试");
//    }

    /**
     * 舱壁隔离的 threadPool
     */
    @GetMapping("bulkhead/get/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myThreadPoolBulkheadFallback", type = Bulkhead.Type.THREADPOOL)  // 使用线程池
    public CompletableFuture<ResultData> myBulkheadTHREADPOOL(@PathVariable("id") Integer id){
//        System.out.println(Thread.currentThread().getName() + "\t" + "---开始进入");
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(Thread.currentThread().getName() + "\t" + "---准备离开");

        return CompletableFuture.supplyAsync(()-> payFeignApi.getInfoByBulkhead(id)
                .setMessage("hello, bulkhead! inputId: " + id + IdUtil.simpleUUID() + " Bulkhead.Type.THREADPOOL"));
    }

    // 舱壁隔离的fallback兜底 : 参数加上Throwable
    public CompletableFuture<ResultData> myThreadPoolBulkheadFallback(Integer id, Throwable e){
        return CompletableFuture.supplyAsync(()-> ResultData.fail(ReturnCodeEnum.RC201, "隔板超出最大线程数量限制, 系统繁忙, 请稍后再试"));
    }

    // 限流算法 :
    // 1. 漏桶算法
    // 2. 令牌桶算法(spring cloud默认)
    // 3. 滚动时间窗 : 容易造成double kill
    // 4. 滑动时间窗 : 不会出现double kill
    @GetMapping("ratelimit/get/{id}")
    @RateLimiter(name = "cloud-payment-service", fallbackMethod = "myRatelimitFallback")
    public ResultData myRatelimit(@PathVariable("id") Integer id){
        return payFeignApi.getInfoByRatelimit(id);
    }

    public ResultData myRatelimitFallback(Integer id, Throwable e){
        return ResultData.fail(ReturnCodeEnum.RC201, "你被限流了, 请稍后再试");

    }

}
