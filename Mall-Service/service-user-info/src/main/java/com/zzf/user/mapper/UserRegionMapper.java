package com.zzf.user.mapper;

import com.zzf.model.entity.user.Region;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-20
 */
@Mapper
public interface UserRegionMapper {
    List<Region> selectByParentCode(Integer code);
    String selectByCode(String Code);
}
