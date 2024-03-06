package com.zzf.manager.mapper;

import com.zzf.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-12
 */
@Mapper
public interface ProductSkuMapper {
    void save(ProductSku productSku);

    List<ProductSku> selectByProductId(Long id);

    void updateById(ProductSku productSku);

    void deleteByProductId(Long id);
}
