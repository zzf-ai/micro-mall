package com.zzf.manager.service.impl;

import com.zzf.manager.mapper.SysRoleMenuMapper;
import com.zzf.manager.service.SysMenuService;
import com.zzf.manager.service.SysRoleMenuService;
import com.zzf.model.dto.system.AssginMenuDto;
import com.zzf.model.entity.system.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzf
 * @date 2024-01-09
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(Long roleId) {
        //查询所有菜单
        List<SysMenu> sysMenuList = sysMenuService.findNodes();

        // 查询当前角色的菜单数据
        List<Long> roleMenuIds = sysRoleMenuMapper.findSysRoleMenuByRoleId(roleId);

        Map<String, Object> map = new HashMap<>();
        map.put("sysMenuList", sysMenuList);
        map.put("roleMenuIds", roleMenuIds);
        return map;
    }

    @Override
    public void doAssign(AssginMenuDto assginMenuDto) {
        // 根据角色的id删除其所对应的菜单数据
        sysRoleMenuMapper.deleteByRoleId(assginMenuDto.getRoleId());

        // 获取菜单的id
        List<Map<String, Number>> menuInfo = assginMenuDto.getMenuIdList();
        if(menuInfo != null && menuInfo.size() > 0) {
            sysRoleMenuMapper.doAssign(assginMenuDto);
        }
    }
}
