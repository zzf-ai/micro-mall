package com.zzf.feign.product;

import com.zzf.model.dto.product.SkuSaleDto;
import com.zzf.model.entity.product.ProductSku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-20
 */
@FeignClient(value = "service-product")
public interface ProductFeignClient {

    /**
     * @Description: 远程调用：根据商品skuId获取商品sku信息
     * @param skuId
     */
    @GetMapping("/api/product/getBySkuId/{skuId}")
    ProductSku getBySkuId(@PathVariable Long skuId);

    /**
     * 更新商品sku销量
     * @param skuSaleDtoList
     * @return
     */
    @PostMapping("/api/product/updateSkuSaleNum")
    Boolean updateSkuSaleNum(@RequestBody List<SkuSaleDto> skuSaleDtoList);
}
