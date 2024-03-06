package com.zzf.model.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zzf
 * @date 2024-01-13
 */
@Data
@Schema(description = "搜索条件实体类")
public class OrderStatisticsDto {

    @Schema(description = "开始时间")
    private String createTimeBegin;

    @Schema(description = "结束时间")
    private String createTimeEnd;

}
