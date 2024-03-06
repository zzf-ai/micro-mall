package com.zzf.model.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zzf
 * @date 2024-01-24
 */
@Data
@Schema(description = "商品sku销量实体类")
public class SkuSaleDto {

    @Schema(description = "商品skuId")
    private Long skuId;

    @Schema(description = "商品sku销量")
    private Integer num;
}
