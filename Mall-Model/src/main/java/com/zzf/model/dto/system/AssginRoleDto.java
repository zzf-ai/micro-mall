package com.zzf.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-08
 */
@Data
@Schema(description = "分配角色请求参数实体类")
public class AssginRoleDto {

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "角色id的List集合")
    private List<Long> roleIdList;

}
