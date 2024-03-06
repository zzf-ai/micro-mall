package com.zzf.manager.utils;

import com.zzf.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzf
 * @date 2024-01-08
 */
//封装树形菜单
public class MenuHelper {
    /**
     * 使用递归方法建菜单
     * @param sysMenuList
     * @return
     */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        List<SysMenu> trees = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            //找到递归入口条件是 Parent_id = 0
            if (sysMenu.getParentId().longValue() == 0) {
                //根据第一层去找下层数据，使用递归完成(第一个参数是当前的第一层菜单，第二个参数是所有菜单的集合)
                trees.add(findChildren(sysMenu,sysMenuList));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    public static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> treeNodes) {
        //对SysMenud的children数据进行初始化
        sysMenu.setChildren(new ArrayList<>());
        for (SysMenu it : treeNodes) {
            if(sysMenu.getId().longValue() == it.getParentId().longValue()) {
                sysMenu.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return sysMenu;
    }
}
