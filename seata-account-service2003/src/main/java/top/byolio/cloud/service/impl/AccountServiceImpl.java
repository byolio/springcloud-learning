package top.byolio.cloud.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import top.byolio.cloud.mapper.AccountMapper;
import top.byolio.cloud.service.AccountService;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: AccountServiceImpl
 * @Description:
 * @Author Byolio
 * @Create 2025/11/22 17:06
 * @Version 1.0
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;

    @Override
    public void decrease(Long userId, Long money) {
        log.info("------->account start");
        accountMapper.decrease(userId, money);
        myTimeOut();
        log.info("------->account end");
    }

    private static void myTimeOut() {
        try {
            TimeUnit.SECONDS.sleep(65);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
