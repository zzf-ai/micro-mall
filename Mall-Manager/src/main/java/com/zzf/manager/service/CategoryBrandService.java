package com.zzf.manager.service;

import com.github.pagehelper.PageInfo;
import com.zzf.model.dto.product.CategoryBrandDto;
import com.zzf.model.entity.product.Brand;
import com.zzf.model.entity.product.CategoryBrand;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-11
 */
public interface CategoryBrandService {
    PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto);

    void save(CategoryBrand categoryBrand);

    void updateById(CategoryBrand categoryBrand);

    void deleteById(Long id);

    List<Brand> findBrandByCategoryId(Long categoryId);
}
