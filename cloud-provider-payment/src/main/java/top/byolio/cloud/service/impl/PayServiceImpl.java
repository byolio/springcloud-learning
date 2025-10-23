package top.byolio.cloud.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.byolio.cloud.entities.Pay;
import top.byolio.cloud.mapper.PayMapper;
import top.byolio.cloud.service.PayService;

import java.util.List;

/**
 * ClassName: PayServiceImpl
 * Description:
 *
 * @Author Byolio
 * @Create 2025/10/21 9:09
 * @Version 1.0
 */
@Service
public class PayServiceImpl implements PayService {

    @Resource
    private PayMapper payMapper;

    @Override
    public int add(Pay pay) {
        return payMapper.insertSelective(pay);
        // insertSelective会忽略null的值, 改用默认值
    }

    @Override
    public int delete(Integer id) {
        return payMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Pay pay) {
        return payMapper.updateByPrimaryKeySelective(pay);
    }

    @Override
    public Pay getById(Integer id) {
        return payMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Pay> getAll() {
        return payMapper.selectAll();
    }
}
