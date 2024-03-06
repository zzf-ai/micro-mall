package com.zzf.user.service;

import com.zzf.model.entity.user.Region;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-20
 */
public interface UserRegionService {
    List<Region> selectByParentCode(Integer code);
}
