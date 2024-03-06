package com.zzf.manager.mapper;

import com.zzf.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zzf
 * @date 2024-01-12
 */
@Mapper
public interface ProductDetailsMapper {
    void save(ProductDetails productDetails);

    ProductDetails selectByProductId(Long id);

    void updateById(ProductDetails productDetails);

    void deleteByProductId(Long id);
}
