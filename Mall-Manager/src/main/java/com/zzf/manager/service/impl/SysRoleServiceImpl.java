package com.zzf.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzf.manager.mapper.SysRoleMapper;
import com.zzf.manager.mapper.SysRoleUserMapper;
import com.zzf.manager.service.SysRoleService;
import com.zzf.model.dto.system.SysRoleDto;
import com.zzf.model.entity.system.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzf
 * @date 2024-01-06
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Override
    public PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit) {
        //设置分页参数
        PageHelper.startPage(current, limit);
        //条件查询
        List<SysRole> list = sysRoleMapper.findByPage(sysRoleDto);

        //封装pageInfo对象
        PageInfo<SysRole> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public void saveSysRole(SysRole sysRole) {
        sysRoleMapper.saveSysRole(sysRole);
    }

    @Override
    public void updateSysRole(SysRole sysRole) {
        sysRoleMapper.updateSysRole(sysRole);
    }

    @Override
    public void deleteById(Long roleId) {
        sysRoleMapper.deleteById(roleId);
    }

    @Override
    public Map<String, Object> findAllRoles(Long userId) {
        //1、查询所有数据
        List<SysRole> sysRoleList = sysRoleMapper.findAllRoles();

        //根据userId查询用户分配过的角色id
        List<Long> roleIds = sysRoleUserMapper.findSysUserRoleByUserId(userId);
        // 构建响应结果数据
        Map<String , Object> resultMap = new HashMap<>();
        resultMap.put("allRolesList" , sysRoleList);
        resultMap.put("sysUserRoles" , roleIds);
        return resultMap;
    }
}
