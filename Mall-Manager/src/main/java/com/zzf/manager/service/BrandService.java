package com.zzf.manager.service;

import com.github.pagehelper.PageInfo;
import com.zzf.model.entity.product.Brand;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-11
 */
public interface BrandService {
    PageInfo<Brand> findByPage(Integer page, Integer limit);

    void save(Brand brand);

    void updateById(Brand brand);

    void deleteById(Long id);

    List<Brand> findAll();
}
