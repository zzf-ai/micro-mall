package com.zzf.manager.controller;

import com.github.pagehelper.PageInfo;
import com.zzf.common.log.annotation.Log;
import com.zzf.common.log.enums.BusinessType;
import com.zzf.common.log.enums.OperatorType;
import com.zzf.manager.service.SysRoleService;
import com.zzf.model.dto.system.SysRoleDto;
import com.zzf.model.entity.system.SysRole;
import com.zzf.model.vo.common.Result;
import com.zzf.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author zzf
 * @date 2024-01-06
 */
@Tag(name = "角色管理接口")
@RestController
@RequestMapping(value = "/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Operation(summary = "获取角色列表")
    @PostMapping("/findByPage/{current}/{limit}")
    public Result findByPage(@PathVariable("current") Integer current, @PathVariable("limit") Integer limit,
                             @RequestBody SysRoleDto sysRoleDto){
        PageInfo<SysRole> pageInfo = sysRoleService.findByPage(sysRoleDto, current, limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @Log(title = "角色", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    @Operation(summary = "添加角色")
    @PostMapping("/saveSysRole")
    public Result saveSysRole(@RequestBody SysRole sysRole){
        sysRoleService.saveSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Log(title = "角色", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    @Operation(summary = "修改角色")
    @PutMapping("/updateSysRole")
    public Result updateSysRole(@RequestBody SysRole sysRole){
        sysRoleService.updateSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Log(title = "角色", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    @Operation(summary = "删除角色")
    @DeleteMapping("/deleteById/{roleId}")
    public Result deleteById(@PathVariable("roleId") Long roleId){
        sysRoleService.deleteById(roleId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "指定用户的角色数据")
    @GetMapping("/findAllRoles/{userId}")
    public Result findAllRoles(@PathVariable(value = "userId") Long userId){
        Map<String, Object> map = sysRoleService.findAllRoles(userId);
        return Result.build(map, ResultCodeEnum.SUCCESS);
    }

}
