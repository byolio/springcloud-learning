package top.byolio.cloud.service;


import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: AccountService
 * @Description:
 * @Author Byolio
 * @Create 2025/11/22 17:05
 * @Version 1.0
 */
public interface AccountService {
    void decrease(Long userId, Long money);
}
