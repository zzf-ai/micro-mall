package com.zzf.manager.service;

import com.github.pagehelper.PageInfo;
import com.zzf.model.entity.product.ProductSpec;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-12
 */
public interface ProductSpecService {
    PageInfo<ProductSpec> findByPage(Integer page, Integer limit);

    void save(ProductSpec productSpec);

    void updateById(ProductSpec productSpec);

    void deleteById(Long id);

    List<ProductSpec> findAll();
}
