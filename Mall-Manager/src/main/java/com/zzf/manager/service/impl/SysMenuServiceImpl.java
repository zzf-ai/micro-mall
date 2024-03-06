package com.zzf.manager.service.impl;

import com.zzf.common.exception.CustomException;
import com.zzf.manager.mapper.SysMenuMapper;
import com.zzf.manager.mapper.SysRoleMenuMapper;
import com.zzf.manager.service.SysMenuService;
import com.zzf.manager.utils.MenuHelper;
import com.zzf.model.entity.system.SysMenu;
import com.zzf.model.entity.system.SysUser;
import com.zzf.model.vo.common.ResultCodeEnum;
import com.zzf.model.vo.system.SysMenuVo;
import com.zzf.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zzf
 * @date 2024-01-08
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenu> findNodes() {
        List<SysMenu> sysMenuList = sysMenuMapper.findAll();
        if(CollectionUtils.isEmpty(sysMenuList)){
            return null;
        }
        //调用工具类的方法，将List集合封装为前端可以解析的数据
        List<SysMenu> treeList = MenuHelper.buildTree(sysMenuList); //构建树形数据
        return treeList;
    }

    @Override
    public void save(SysMenu sysMenu) {
        sysMenuMapper.save(sysMenu);
        //新添加子菜单，需要把父菜单变为半开状态1
        updateSysRoleMenu(sysMenu) ;
    }

    @Override
    public void updateById(SysMenu sysMenu) {
        sysMenuMapper.updateById(sysMenu);
    }

    @Override
    public void removeById(Long id) {

        //先查询当前菜单是否包含子菜单
        int count = sysMenuMapper.countByParentId(id);
        //如果存在不允许删除
        if (count > 0) {
            throw new CustomException(ResultCodeEnum.NODE_ERROR);
        }
        sysMenuMapper.deleteById(id);
    }

    @Override
    public List<SysMenuVo> findMenusByUserId() {
        //获取当前用户id
        SysUser sysUser = AuthContextUtil.get();
        Long userId = sysUser.getId();

        // 根据id查询可以操作的菜单
        List<SysMenu> sysMenuList = sysMenuMapper.selectListByUserId(userId);
        //封装要求数据格式，构建树形数据
        List<SysMenu> sysMenuTreeList = MenuHelper.buildTree(sysMenuList);
        return this.buildMenus(sysMenuTreeList);
    }

    // 将List<SysMenu>对象转换成List<SysMenuVo>对象
    private List<SysMenuVo> buildMenus(List<SysMenu> menus) {

        List<SysMenuVo> sysMenuVoList = new ArrayList<>();
        for (SysMenu sysMenu : menus) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            List<SysMenu> children = sysMenu.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                sysMenuVo.setChildren(buildMenus(children));
            }
            sysMenuVoList.add(sysMenuVo);
        }
        return sysMenuVoList;
    }

    private void updateSysRoleMenu(SysMenu sysMenu) {

        // 查询是否存在父节点
        SysMenu parentMenu = sysMenuMapper.selectParentMenu(sysMenu.getParentId());

        if(parentMenu != null) {
            // 将父菜单 ishalf 值设置为半开 1
            sysRoleMenuMapper.updateSysRoleMenuIsHalf(parentMenu.getId());
            // 递归调用
            updateSysRoleMenu(parentMenu);
        }

    }
}
