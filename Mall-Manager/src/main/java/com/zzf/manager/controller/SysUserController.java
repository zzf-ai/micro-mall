package com.zzf.manager.controller;

import com.github.pagehelper.PageInfo;
import com.zzf.common.log.annotation.Log;
import com.zzf.common.log.enums.BusinessType;
import com.zzf.common.log.enums.OperatorType;
import com.zzf.manager.service.SysUserService;
import com.zzf.model.dto.system.AssginRoleDto;
import com.zzf.model.dto.system.SysUserDto;
import com.zzf.model.entity.system.SysRole;
import com.zzf.model.entity.system.SysUser;
import com.zzf.model.vo.common.Result;
import com.zzf.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zzf
 * @date 2024-01-07
 */
@Tag(name = "用户管理接口")
@RestController
@RequestMapping(value = "/admin/system/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Operation(summary = "用户条件分页查询接口")
    @PostMapping(value = "/findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<SysRole>> findByPage(@RequestBody SysUserDto sysUserDto,
                                                @PathVariable(value = "pageNum") Integer pageNum,
                                                @PathVariable(value = "pageSize") Integer pageSize) {
        PageInfo<SysUser> pageInfo = sysUserService.findByPage(sysUserDto , pageNum , pageSize);
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS);
    }

    @Log(title = "用户", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    @Operation(summary = "用户添加接口")
    @PostMapping(value = "/saveSysUser")
    public Result saveSysUser(@RequestBody SysUser sysUser) {
        sysUserService.saveSysUser(sysUser);
        return Result.build(null , ResultCodeEnum.SUCCESS);
    }

    @Log(title = "用户", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    @Operation(summary = "用户修改接口")
    @PutMapping(value = "/updateSysUser")
    public Result updateSysUser(@RequestBody SysUser sysUser) {
        sysUserService.updateSysUser(sysUser);
        return Result.build(null , ResultCodeEnum.SUCCESS);
    }

    @Log(title = "用户", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    @Operation(summary = "用户删除接口")
    @DeleteMapping(value = "/deleteById/{userId}")
    public Result deleteById(@PathVariable(value = "userId") Long userId) {
        sysUserService.deleteById(userId) ;
        return Result.build(null , ResultCodeEnum.SUCCESS);
    }

    @Log(title = "用户", businessType = BusinessType.ASSIGN_ROLE, operatorType = OperatorType.MANAGE)
    @Operation(summary = "用户分配角色保存分配数据")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleDto assginRoleDto) {
        sysUserService.doAssign(assginRoleDto) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

}
