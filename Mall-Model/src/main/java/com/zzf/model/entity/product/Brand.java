package com.zzf.model.entity.product;

import com.zzf.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zzf
 * @date 2024-01-11
 */
@Data
@Schema(description = "品牌实体类")
public class Brand extends BaseEntity {

    @Schema(description = "品牌名称")
    private String name;

    @Schema(description = "品牌logo")
    private String logo;

}
