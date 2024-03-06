package com.zzf.feign.order;

import com.zzf.model.entity.order.OrderInfo;
import com.zzf.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zzf
 * @date 2024-01-23
 */
@FeignClient(value = "service-order")
public interface OrderFeignClient {

    @GetMapping("/api/order/orderInfo/auth/getOrderInfoByOrderNo/{orderNo}")
    Result<OrderInfo> getOrderInfoByOrderNo(@PathVariable String orderNo);

    @GetMapping("/api/order/orderInfo/auth/updateOrderStatusPayed/{orderNo}/{orderStatus}")
    Result updateOrderStatus(@PathVariable(value = "orderNo") String orderNo, @PathVariable(value = "orderStatus") Integer orderStatus);
}
