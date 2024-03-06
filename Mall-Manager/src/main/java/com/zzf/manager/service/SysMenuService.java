package com.zzf.manager.service;

import com.zzf.model.entity.system.SysMenu;
import com.zzf.model.vo.system.SysMenuVo;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-08
 */
public interface SysMenuService {
    List<SysMenu> findNodes();

    void save(SysMenu sysMenu);

    void updateById(SysMenu sysMenu);

    void removeById(Long id);

    List<SysMenuVo> findMenusByUserId();
}
