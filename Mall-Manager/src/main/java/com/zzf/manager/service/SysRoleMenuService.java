package com.zzf.manager.service;

import com.zzf.model.dto.system.AssginMenuDto;

import java.util.Map;

/**
 * @author zzf
 * @date 2024-01-09
 */
public interface SysRoleMenuService {
    Map<String, Object> findSysRoleMenuByRoleId(Long roleId);

    void doAssign(AssginMenuDto assginMenuDto);
}
