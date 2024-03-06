package com.zzf.user.controller;

import com.zzf.model.entity.user.Region;
import com.zzf.model.entity.user.UserAddress;
import com.zzf.model.vo.common.Result;
import com.zzf.model.vo.common.ResultCodeEnum;
import com.zzf.user.service.UserAddressService;
import com.zzf.user.service.UserRegionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-20
 */
@Tag(name = "用户地址接口")
@RestController
@RequestMapping(value="/api/user")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private UserRegionService userRegionService;

    @Operation(summary = "获取用户地址列表")
    @GetMapping("userAddress/auth/findUserAddressList")
    public Result<List<UserAddress>> findUserAddressList() {
        List<UserAddress> list = userAddressService.findUserAddressList();
        return Result.build(list , ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "收货地址省市区显示")
    @GetMapping ("region/findByParentCode/{code}")
    public Result register(@PathVariable("code") Integer code) {
        List<Region> regionList = userRegionService.selectByParentCode(code);
        return Result.build(regionList , ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "用户收货地址新增")
    @PostMapping("userAddress/auth/save")
    public Result save(@RequestBody UserAddress userAddress) {
        userAddressService.save(userAddress);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @Operation(summary = "用户收货地址修改")
    @PutMapping("userAddress/auth/updateById")
    public Result updateById(@RequestBody UserAddress userAddress) {
        userAddressService.updateById(userAddress);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @Operation(summary = "用户删除收货地址")
    @DeleteMapping("userAddress/auth/removeById/{id}")
    public Result removeById(@PathVariable Long id) {
        userAddressService.removeById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @Operation(summary = "获取地址信息")
    @GetMapping("userAddress/getUserAddress/{id}")
    public UserAddress getUserAddress(@PathVariable Long id) {
        return userAddressService.getById(id);
    }


}
