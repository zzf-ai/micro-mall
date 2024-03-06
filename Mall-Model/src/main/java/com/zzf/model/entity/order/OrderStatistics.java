package com.zzf.model.entity.order;

import com.zzf.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zzf
 * @date 2024-01-13
 */
@Data
@Schema(description = "订单数据统计类")
public class OrderStatistics extends BaseEntity {

    @Schema(description = "订单日期")
    private Date orderDate;

    @Schema(description = "订单总金额")
    private BigDecimal totalAmount;

    @Schema(description = "订单总数量")
    private Integer totalNum;

}
