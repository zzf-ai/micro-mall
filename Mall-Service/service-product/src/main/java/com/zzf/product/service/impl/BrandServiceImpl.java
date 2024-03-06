package com.zzf.product.service.impl;

import com.zzf.model.entity.product.Brand;
import com.zzf.product.mapper.BrandMapper;
import com.zzf.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-17
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {
        return brandMapper.findAll();
    }
}
