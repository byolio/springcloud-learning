package top.byolio.cloud.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.byolio.cloud.mapper.StorageMapper;
import top.byolio.cloud.service.StorageService;

/**
 * @ClassName: StorageServiceImpl
 * @Description:
 * @Author Byolio
 * @Create 2025/11/22 16:28
 * @Version 1.0
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    @Resource
    private StorageMapper storageMapper;


    @Override
    public void decrease(Long productId, Integer count) {
        log.info("---->storage-service中扣减库存开始");
        storageMapper.decrease(productId, count);
        log.info("---->storage-service中扣减库存完毕");
    }
}
