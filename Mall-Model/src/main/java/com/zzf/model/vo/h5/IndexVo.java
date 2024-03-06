package com.zzf.model.vo.h5;

import com.zzf.model.entity.product.Category;
import com.zzf.model.entity.product.ProductSku;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-16
 */
@Data
@AllArgsConstructor
@Schema(description = "前台系统首页结果实体类")
public class IndexVo {

    @Schema(description = "一级分类的类别数据")
    private List<Category> categoryList;

    @Schema(description = "畅销商品列表数据")
    private List<ProductSku> productSkuList;

}
