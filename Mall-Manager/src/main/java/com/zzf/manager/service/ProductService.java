package com.zzf.manager.service;

import com.github.pagehelper.PageInfo;
import com.zzf.model.dto.product.ProductDto;
import com.zzf.model.entity.product.Product;

/**
 * @author zzf
 * @date 2024-01-12
 */
public interface ProductService {
    PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto);

    void save(Product product);

    Product getById(Long id);

    void updateById(Product product);

    void deleteById(Long id);

    void updateAuditStatus(Long id, Integer auditStatus);

    void updateStatus(Long id, Integer status);
}
