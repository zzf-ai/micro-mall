package com.zzf.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zzf
 * @date 2024-01-07
 */
@Data
@Schema(description = "请求参数实体类")
public class SysUserDto {

    @Schema(description = "搜索关键字")
    private String keyword;

    @Schema(description = "开始时间")
    private String createTimeBegin;

    @Schema(description = "结束时间")
    private String createTimeEnd;

}
