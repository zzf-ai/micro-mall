package com.zzf.manager.controller;

import com.github.pagehelper.PageInfo;
import com.zzf.common.log.annotation.Log;
import com.zzf.common.log.enums.BusinessType;
import com.zzf.common.log.enums.OperatorType;
import com.zzf.manager.service.CategoryBrandService;
import com.zzf.manager.service.CategoryService;
import com.zzf.model.dto.product.CategoryBrandDto;
import com.zzf.model.entity.product.Brand;
import com.zzf.model.entity.product.CategoryBrand;
import com.zzf.model.vo.common.Result;
import com.zzf.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-11
 */
@Tag(name = "分类品牌信息接口")
@RestController
@RequestMapping(value = "/admin/product/categoryBrand")
public class CategoryBrandController {

    @Autowired
    private CategoryBrandService categoryBrandService;

    @Operation(summary = "分类品牌条件分页查询")
    @GetMapping("/{page}/{limit}")
    public Result<PageInfo<CategoryBrand>> findByPage(@PathVariable Integer page, @PathVariable Integer limit, CategoryBrandDto CategoryBrandDto) {
        PageInfo<CategoryBrand> pageInfo = categoryBrandService.findByPage(page, limit, CategoryBrandDto);
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS);
    }

    @Log(title = "分类品牌", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    @Operation(summary = "分类品牌添加")
    @PostMapping("/save")
    public Result save(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.save(categoryBrand);
        return Result.build(null , ResultCodeEnum.SUCCESS);
    }

    @Log(title = "分类品牌", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    @Operation(summary = "分类品牌修改")
    @PutMapping("updateById")
    public Result updateById(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.updateById(categoryBrand);
        return Result.build(null , ResultCodeEnum.SUCCESS);
    }

    @Log(title = "分类品牌", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    @Operation(summary = "分类品牌删除")
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Long id) {
        categoryBrandService.deleteById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "根据分类ID查询品牌数据")
    @GetMapping("/findBrandByCategoryId/{categoryId}")
    public Result findBrandByCategoryId(@PathVariable Long categoryId) {
        List<Brand> brandList =   categoryBrandService.findBrandByCategoryId(categoryId);
        return Result.build(brandList , ResultCodeEnum.SUCCESS) ;
    }
}
