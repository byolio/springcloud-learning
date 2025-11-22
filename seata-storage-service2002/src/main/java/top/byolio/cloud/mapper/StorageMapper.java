package top.byolio.cloud.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import top.byolio.cloud.entities.Storage;

public interface StorageMapper extends Mapper<Storage> {
    /**
     * 扣减库存
     * @param productId
     * @param count
     */
    void decrease(@Param("productId") Long productId,@Param("count") Integer count);
}
