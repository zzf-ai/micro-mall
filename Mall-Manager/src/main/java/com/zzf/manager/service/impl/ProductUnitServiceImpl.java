package com.zzf.manager.service.impl;

import com.zzf.manager.mapper.ProductUnitMapper;
import com.zzf.manager.service.ProductUnitService;
import com.zzf.model.entity.product.ProductUnit;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-12
 */
@Service
public class ProductUnitServiceImpl implements ProductUnitService {

    private ProductUnitMapper productUnitMapper;

    @Override
    public List<ProductUnit> findAll() {
        return productUnitMapper.findAll();
    }
}
