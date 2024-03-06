package com.zzf.manager.mapper;

import com.zzf.model.entity.product.Category;
import com.zzf.model.vo.product.CategoryExcelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-10
 */
@Mapper
public interface CategoryMapper {
    List<Category> selectByParentId(Long parentId);

    int countByParentId(Long id);

    List<Category> selectAll();

    void batchInsert(List<CategoryExcelVo> cachedDataList);
}
