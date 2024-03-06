package com.zzf.manager.mapper;

import com.zzf.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-08
 */
@Mapper
public interface SysMenuMapper {
    List<SysMenu> findAll();

    void save(SysMenu sysMenu);

    void updateById(SysMenu sysMenu);

    int countByParentId(Long id);

    void deleteById(Long id);

    List<SysMenu> selectListByUserId(Long userId);

    SysMenu selectParentMenu(Long parentId);
}
