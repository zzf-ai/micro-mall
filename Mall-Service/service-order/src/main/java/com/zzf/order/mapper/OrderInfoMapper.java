package com.zzf.order.mapper;

import com.zzf.model.entity.order.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-21
 */
@Mapper
public interface OrderInfoMapper {
    void save(OrderInfo orderInfo);

    OrderInfo getById(Long orderId);

    List<OrderInfo> findOrderInfoPage(Long userId, Integer orderStatus);

    OrderInfo getByOrderNo(String orderNo);

    void updateById(OrderInfo orderInfo);
}
