package com.zzf.product.controller;

import com.github.pagehelper.PageInfo;
import com.zzf.model.dto.h5.ProductSkuDto;
import com.zzf.model.dto.product.SkuSaleDto;
import com.zzf.model.entity.product.ProductSku;
import com.zzf.model.vo.common.Result;
import com.zzf.model.vo.common.ResultCodeEnum;
import com.zzf.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-17
 */
@Tag(name = "商品列表管理")
@RestController
@RequestMapping(value="/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @Operation(summary = "分页查询")
    @GetMapping(value = "/{page}/{limit}")
    public Result findByPage(@Parameter(name = "page", description = "当前页码", required = true) @PathVariable Integer page,
                                                   @Parameter(name = "limit", description = "每页记录数", required = true) @PathVariable Integer limit,
                                                   @Parameter(name = "productSkuDto", description = "搜索条件对象", required = false) ProductSkuDto productSkuDto) {
        PageInfo<ProductSku> pageInfo = productService.findByPage(page, limit, productSkuDto);
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }

    //远程调用部分

    @Operation(summary = "获取商品sku信息")
    @GetMapping("getBySkuId/{skuId}")
    public ProductSku getBySkuId(@Parameter(name = "skuId", description = "商品skuId", required = true) @PathVariable Long skuId) {
        ProductSku productSku = productService.getBySkuId(skuId);
        return productSku;
    }

    @Operation(summary = "更新商品sku销量")
    @PostMapping("updateSkuSaleNum")
    public Boolean updateSkuSaleNum(@RequestBody List<SkuSaleDto> skuSaleDtoList) {
        return productService.updateSkuSaleNum(skuSaleDtoList);
    }


}
