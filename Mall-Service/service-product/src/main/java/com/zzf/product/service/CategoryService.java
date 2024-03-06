package com.zzf.product.service;

import com.zzf.model.entity.product.Category;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-15
 */
public interface CategoryService {
    List<Category> findFirstCategory();

    List<Category> findCategoryTree();
}
