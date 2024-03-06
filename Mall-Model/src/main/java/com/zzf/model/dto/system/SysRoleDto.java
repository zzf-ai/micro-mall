package com.zzf.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zzf
 * @date 2024-01-06
 */
@Data
@Schema(description = "请求参数实体类")
public class SysRoleDto {

    @Schema(description = "角色名称")
    private String roleName;

}
