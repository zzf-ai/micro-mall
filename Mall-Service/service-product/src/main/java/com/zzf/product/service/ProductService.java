package com.zzf.product.service;

import com.github.pagehelper.PageInfo;
import com.zzf.model.dto.h5.ProductSkuDto;
import com.zzf.model.dto.product.SkuSaleDto;
import com.zzf.model.entity.product.ProductSku;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-17
 */
public interface ProductService {

    List<ProductSku> findProductSkuBySal();

    PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto);

    ProductSku getBySkuId(Long skuId);

    Boolean updateSkuSaleNum(List<SkuSaleDto> skuSaleDtoList);
}
