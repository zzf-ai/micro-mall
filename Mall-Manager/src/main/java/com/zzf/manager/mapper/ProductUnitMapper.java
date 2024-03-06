package com.zzf.manager.mapper;

import com.zzf.model.entity.product.ProductUnit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-12
 */
@Mapper
public interface ProductUnitMapper {
    List<ProductUnit> findAll();
}
