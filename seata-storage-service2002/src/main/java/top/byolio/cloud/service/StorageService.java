package top.byolio.cloud.service;

/**
 * @ClassName: StorageService
 * @Description:
 * @Author Byolio
 * @Create 2025/11/22 16:27
 * @Version 1.0
 */
public interface StorageService {
    void decrease(Long productId, Integer count);
}
