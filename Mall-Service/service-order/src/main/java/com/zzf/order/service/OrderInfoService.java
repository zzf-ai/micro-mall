package com.zzf.order.service;

import com.github.pagehelper.PageInfo;
import com.zzf.model.dto.order.OrderInfoDto;
import com.zzf.model.entity.order.OrderInfo;
import com.zzf.model.vo.order.TradeVo;

/**
 * @author zzf
 * @date 2024-01-21
 */
public interface OrderInfoService {
    TradeVo getTrade();

    Long submitOrder(OrderInfoDto orderInfoDto);

    OrderInfo getOrderInfo(Long orderId);

    TradeVo buy(Long skuId);


    PageInfo<OrderInfo> findOrderInfoPage(Integer page, Integer limit, Integer orderStatus);

    OrderInfo getByOrderNo(String orderNo);

    void updateOrderStatus(String orderNo, Integer orderStatus);
}
