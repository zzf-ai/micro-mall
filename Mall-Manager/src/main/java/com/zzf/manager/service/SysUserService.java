package com.zzf.manager.service;

import com.github.pagehelper.PageInfo;
import com.zzf.model.dto.system.AssginRoleDto;
import com.zzf.model.dto.system.LoginDto;
import com.zzf.model.dto.system.SysRoleDto;
import com.zzf.model.dto.system.SysUserDto;
import com.zzf.model.entity.system.SysRole;
import com.zzf.model.entity.system.SysUser;
import com.zzf.model.vo.system.LoginVo;
import com.zzf.model.vo.system.SysMenuVo;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-04
 */
public interface SysUserService {
    LoginVo login(LoginDto loginDto);

    SysUser getUserInfo(String token);

    void logout(String token);

    PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize);

    void saveSysUser(SysUser sysUser);

    void updateSysUser(SysUser sysUser);

    void deleteById(Long userId);

    void doAssign(AssginRoleDto assginRoleDto);

}
