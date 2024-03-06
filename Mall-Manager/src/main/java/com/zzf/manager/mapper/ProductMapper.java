package com.zzf.manager.mapper;

import com.zzf.model.dto.product.ProductDto;
import com.zzf.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-12
 */
@Mapper
public interface ProductMapper {
    List<Product> findByPage(ProductDto productDto);

    void save(Product product);

    Product selectById(Long id);

    void updateById(Product product);

    void deleteById(Long id);
}
