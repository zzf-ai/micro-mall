package com.zzf.product.controller;

import com.zzf.model.entity.product.Category;
import com.zzf.model.vo.common.Result;
import com.zzf.model.vo.common.ResultCodeEnum;
import com.zzf.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-16
 */
@Tag(name = "分类接口管理")
@RestController
@RequestMapping(value="/api/product/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "获取分类树形数据")
    @GetMapping("findCategoryTree")
    public Result findCategoryTree(){
        List<Category> list = categoryService.findCategoryTree();
        return Result.build(list,  ResultCodeEnum.SUCCESS);
    }
}
