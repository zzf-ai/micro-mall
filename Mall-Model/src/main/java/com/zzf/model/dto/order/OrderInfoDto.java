package com.zzf.model.dto.order;

import com.zzf.model.entity.order.OrderItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zzf
 * @date 2024-01-22
 */
@Data
@Schema(description = "提交订单页面的信息实体类")
public class OrderInfoDto {

    @Schema(description = "送货地址id")
    private Long userAddressId;

    @Schema(description = "运费")
    private BigDecimal feightFee;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "订单明细")
    private List<OrderItem> orderItemList;
}
