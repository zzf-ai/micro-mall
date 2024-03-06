package com.zzf.product.mapper;

import com.zzf.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-17
 */
@Mapper
public interface BrandMapper {
    List<Brand> findAll();
}
