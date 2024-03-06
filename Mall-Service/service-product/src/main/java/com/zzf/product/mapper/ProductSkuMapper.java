package com.zzf.product.mapper;

import com.zzf.model.dto.h5.ProductSkuDto;
import com.zzf.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-16
 */
@Mapper
public interface ProductSkuMapper {
    List<ProductSku> findProductSkuBySale();

    List<ProductSku> findByPage(ProductSkuDto productSkuDto);

    ProductSku getById(Long skuId);

    void updateSale(Long skuId, Integer num);
}
