package com.zzf.model.entity.product;

import com.zzf.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zzf
 * @date 2024-01-12
 */
@Data
@Schema(description = "ProductDetails")
public class ProductDetails extends BaseEntity {

    @Schema(description = "商品ID")
    private Long productId;

    @Schema(description = "商品详情图片列表")
    private String imageUrls;

}
