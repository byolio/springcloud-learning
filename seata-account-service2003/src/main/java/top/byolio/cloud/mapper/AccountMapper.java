package top.byolio.cloud.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import top.byolio.cloud.entities.Account;

public interface AccountMapper extends Mapper<Account> {
    /**
     *
     * @param userId
     * @param money
     */
    void decrease(@Param("userId") Long userId, @Param("money") Long money);
}