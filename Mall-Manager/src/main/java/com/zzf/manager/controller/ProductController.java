package com.zzf.manager.controller;

import com.github.pagehelper.PageInfo;
import com.zzf.common.log.annotation.Log;
import com.zzf.common.log.enums.BusinessType;
import com.zzf.common.log.enums.OperatorType;
import com.zzf.manager.service.ProductService;
import com.zzf.model.dto.product.ProductDto;
import com.zzf.model.entity.product.Product;
import com.zzf.model.vo.common.Result;
import com.zzf.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zzf
 * @date 2024-01-12
 */
@Tag(name = "商品管理接口")
@RestController
@RequestMapping(value="/admin/product/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "条件分页查询接口")
    @GetMapping(value = "/{page}/{limit}")
    public Result<PageInfo<Product>> findByPage(@PathVariable Integer page, @PathVariable Integer limit, ProductDto productDto) {
        PageInfo<Product> pageInfo = productService.findByPage(page, limit, productDto);
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS);
    }

    @Log(title = "商品", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    @Operation(summary = "新增商品保存接口")
    @PostMapping("/save")
    public Result save(@RequestBody Product product) {
        productService.save(product);
        return Result.build(null , ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "根据id查询商品详情_数据回显")
    @GetMapping("/getById/{id}")
    public Result<Product> getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return Result.build(product , ResultCodeEnum.SUCCESS);
    }

    @Log(title = "商品", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGE)
    @Operation(summary = "修改_保存商品数据接口")
    @PutMapping("/updateById")
    public Result updateById(@Parameter(name = "product", description = "请求参数实体类", required = true) @RequestBody Product product) {
        productService.updateById(product);
        return Result.build(null , ResultCodeEnum.SUCCESS);
    }

    @Log(title = "商品", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    @Operation(summary = "删除商品数据")
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@Parameter(name = "id", description = "商品id", required = true) @PathVariable Long id) {
        productService.deleteById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS);
    }

    @Log(title = "商品", businessType = BusinessType.AUDIT, operatorType = OperatorType.MANAGE)
    @Operation(summary = "商品审核")
    @GetMapping("/updateAuditStatus/{id}/{auditStatus}")
    public Result updateAuditStatus(@PathVariable Long id, @PathVariable Integer auditStatus) {
        productService.updateAuditStatus(id, auditStatus);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @Log(title = "商品", businessType = BusinessType.ON_OFF, operatorType = OperatorType.MANAGE)
    @Operation(summary = "商品上架下架")
    @GetMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        productService.updateStatus(id, status);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
}
