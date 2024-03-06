package com.zzf.user.mapper;

import com.zzf.model.entity.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zzf
 * @date 2024-01-18
 */
@Mapper
public interface UserInfoMapper {
    UserInfo getByUsername(String username);

    void save(UserInfo userInfo);
}
