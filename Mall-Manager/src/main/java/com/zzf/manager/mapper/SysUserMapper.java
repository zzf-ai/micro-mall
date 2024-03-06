package com.zzf.manager.mapper;

import com.zzf.model.dto.system.LoginDto;
import com.zzf.model.dto.system.SysUserDto;
import com.zzf.model.entity.system.SysRole;
import com.zzf.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-04
 */
@Mapper
public interface SysUserMapper {

    SysUser selectUserInfoByUserName(String userName);

    List<SysUser> findByPage(SysUserDto sysUserDto);

    void saveSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void deleteById(Long userId);
}
