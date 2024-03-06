package com.zzf.product.controller;

import com.zzf.model.entity.product.Category;
import com.zzf.model.entity.product.ProductSku;
import com.zzf.model.vo.common.Result;
import com.zzf.model.vo.common.ResultCodeEnum;
import com.zzf.model.vo.h5.IndexVo;
import com.zzf.product.service.CategoryService;
import com.zzf.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-15
 */
@Tag(name = "首页接口管理")
@RestController
@RequestMapping(value="/api/product/index")
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Operation(summary = "获取首页数据")
    @GetMapping
    public Result index(){
        //获取所有一级分类
        List<Category> categoryList = categoryService.findFirstCategory();

        //根据销量排序，获取前20条记录
        List<ProductSku> productSkuList = productService.findProductSkuBySal();

        //封装数据
        IndexVo indexVo = new IndexVo(categoryList, productSkuList);
        return Result.build(indexVo, ResultCodeEnum.SUCCESS);
    }

}
