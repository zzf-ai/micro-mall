package com.zzf.manager.controller;

import com.zzf.common.log.annotation.Log;
import com.zzf.common.log.enums.BusinessType;
import com.zzf.common.log.enums.OperatorType;
import com.zzf.manager.service.SysMenuService;
import com.zzf.model.entity.system.SysMenu;
import com.zzf.model.vo.common.Result;
import com.zzf.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author zzf
 * @date 2024-01-08
 */
@Tag(name = "菜单管理接口")
@RestController
@RequestMapping(value="/admin/system/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @Operation(summary = "获取菜单列表")
    @GetMapping("/findNodes")
    public Result findNodes() {
        List<SysMenu> list = sysMenuService.findNodes();
        return Result.build(list , ResultCodeEnum.SUCCESS) ;
    }

    @Log(title = "菜单", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    @Operation(summary = "添加菜单")
    @PostMapping("/save")
    public Result save(@RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @Log(title = "菜单", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    @Operation(summary = "修改菜单")
    @PutMapping("/updateById")
    public Result updateById(@RequestBody SysMenu sysMenu) {
        sysMenuService.updateById(sysMenu);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @Log(title = "菜单", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    @Operation(summary = "删除菜单")
    @DeleteMapping("/removeById/{id}")
    public Result removeById(@PathVariable Long id) {
        sysMenuService.removeById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
}
