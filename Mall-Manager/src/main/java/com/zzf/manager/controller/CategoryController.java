package com.zzf.manager.controller;

import com.zzf.common.log.annotation.Log;
import com.zzf.common.log.enums.BusinessType;
import com.zzf.common.log.enums.OperatorType;
import com.zzf.manager.service.CategoryService;
import com.zzf.model.entity.product.Category;
import com.zzf.model.vo.common.Result;
import com.zzf.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author zzf
 * @date 2024-01-10
 */
@Tag(name = "商品分类信息接口")
@RestController
@RequestMapping(value="/admin/product/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "分类列表，每次查询一层数据")
    @GetMapping(value = "/findByParentId/{parentId}")
    public Result findByParentId(@PathVariable(value = "parentId") Long parentId) {
        List<Category> list = categoryService.findByParentId(parentId);
        return Result.build(list , ResultCodeEnum.SUCCESS);
    }

    @Log(title = "商品分类信息", businessType = BusinessType.EXPORT, operatorType = OperatorType.MANAGE)
    @Operation(summary = "商品分类信息导出接口")
    @GetMapping(value = "/exportData")
    public void exportData(HttpServletResponse response) {
        categoryService.exportData(response);
    }

    @Log(title = "商品分类信息", businessType = BusinessType.IMPORT, operatorType = OperatorType.MANAGE)
    @Operation(summary = "商品分类信息导入接口")
    @PostMapping("importData")
    public Result importData(MultipartFile file) {
        categoryService.importData(file);
        return Result.build(null , ResultCodeEnum.SUCCESS);
    }
}
