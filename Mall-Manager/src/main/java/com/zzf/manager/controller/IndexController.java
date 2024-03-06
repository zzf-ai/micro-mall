package com.zzf.manager.controller;

import com.zzf.manager.mapper.SysRoleMapper;
import com.zzf.manager.mapper.SysUserMapper;
import com.zzf.manager.service.SysMenuService;
import com.zzf.manager.service.ValidateCodeService;
import com.zzf.model.dto.system.LoginDto;

import com.zzf.manager.service.SysUserService;
import com.zzf.model.dto.system.SysRoleDto;
import com.zzf.model.entity.system.SysRole;
import com.zzf.model.vo.common.Result;
import com.zzf.model.vo.common.ResultCodeEnum;
import com.zzf.model.vo.system.LoginVo;
import com.zzf.model.vo.system.SysMenuVo;
import com.zzf.model.vo.system.ValidateCodeVo;
import com.zzf.utils.AuthContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-04
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping(value = "/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private ValidateCodeService validateCodeService;

    //生成图片验证码
    @GetMapping(value = "/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode(){
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "登录")
    @PostMapping("login")
    public Result login(@RequestBody LoginDto loginDto){

        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }

    //获取当前登录用户信息
    @Operation(summary = "获取当前登录用户信息")
    @GetMapping(value = "/getUserInfo")
    public Result getUserInfo(){
        return Result.build(AuthContextUtil.get(), ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "退出登录")
    @GetMapping(value = "/logout")
    public Result logout(@RequestHeader(name = "token") String token){
        sysUserService.logout(token);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


    @Operation(summary = "获取菜单动态显示")
    @GetMapping("/menus")
    public Result menus(){
        List<SysMenuVo> list = sysMenuService.findMenusByUserId();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}
