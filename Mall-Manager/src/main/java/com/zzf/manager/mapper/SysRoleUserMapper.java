package com.zzf.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-08
 */
@Mapper
public interface SysRoleUserMapper {
    List<Long> findSysUserRoleByUserId(Long userId);

    void doAssign(@Param("userId") Long userId,
                  @Param("roleId") Long roleId);		//添加关联关系
    void deleteByUserId(Long userId);				//根据用户的id删除数据
}

