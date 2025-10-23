package top.byolio.cloud.service;

import top.byolio.cloud.entities.Pay;

import java.util.List;

/**
 * ClassName: PayService
 * Description:
 *
 * @Author Byolio
 * @Create 2025/10/21 9:02
 * @Version 1.0
 */
public interface PayService {
    int add(Pay pay);
    int delete(Integer id);
    int update(Pay pay);
    Pay getById(Integer id);
    List<Pay> getAll();
}
