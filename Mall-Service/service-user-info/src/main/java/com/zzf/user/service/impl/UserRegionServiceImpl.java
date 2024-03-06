package com.zzf.user.service.impl;

import com.zzf.model.entity.user.Region;
import com.zzf.user.mapper.UserRegionMapper;
import com.zzf.user.service.UserRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-20
 */
@Service
public class UserRegionServiceImpl implements UserRegionService {
    @Autowired
    private UserRegionMapper userRegionMapper;
    @Override
    public List<Region> selectByParentCode(Integer code) {
        List<Region> regions = userRegionMapper.selectByParentCode(code);
        return regions;
    }
}
