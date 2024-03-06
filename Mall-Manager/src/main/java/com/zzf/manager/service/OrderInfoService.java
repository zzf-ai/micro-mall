package com.zzf.manager.service;

import com.zzf.model.dto.order.OrderStatisticsDto;
import com.zzf.model.vo.order.OrderStatisticsVo;

/**
 * @author zzf
 * @date 2024-01-13
 */
public interface OrderInfoService {
    OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);
}
