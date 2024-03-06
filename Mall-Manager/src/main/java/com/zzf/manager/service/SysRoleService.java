package com.zzf.manager.service;

import com.github.pagehelper.PageInfo;
import com.zzf.model.dto.system.SysRoleDto;
import com.zzf.model.entity.system.SysRole;

import java.util.Map;

/**
 * @author zzf
 * @date 2024-01-06
 */
public interface SysRoleService {
    PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit);

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deleteById(Long roleId);

    Map<String, Object> findAllRoles(Long userId);
}
