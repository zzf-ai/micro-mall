package com.zzf.model.entity.product;

import com.zzf.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zzf
 * @date 2024-01-12
 */
@Data
@Schema(description = "产品单元实体类")
public class ProductUnit extends BaseEntity {

    @Schema(description = "名称")
    private String name;

}
