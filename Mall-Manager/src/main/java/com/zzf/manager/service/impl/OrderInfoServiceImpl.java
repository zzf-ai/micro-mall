package com.zzf.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.zzf.manager.mapper.OrderStatisticsMapper;
import com.zzf.manager.service.OrderInfoService;
import com.zzf.model.dto.order.OrderStatisticsDto;
import com.zzf.model.entity.order.OrderStatistics;
import com.zzf.model.vo.order.OrderStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zzf
 * @date 2024-01-13
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;

    @Override
    public OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {
        //查询统计结果数据
        List<OrderStatistics> orderStatisticsList = orderStatisticsMapper.selectList(orderStatisticsDto);

        //封装日期列表
        List<String> dateList = orderStatisticsList.stream()
                .map(orderStatistics -> DateUtil.format(orderStatistics.getOrderDate(), "yyyy-mm-dd"))
                .collect(Collectors.toList());

        //封装对应的统计金额列表
        List<BigDecimal> amountList = orderStatisticsList.stream().map(OrderStatistics::getTotalAmount).collect(Collectors.toList());

        // 创建OrderStatisticsVo对象封装响应结果数据
        OrderStatisticsVo orderStatisticsVo = new OrderStatisticsVo();
        orderStatisticsVo.setDateList(dateList);
        orderStatisticsVo.setAmountList(amountList);
        return orderStatisticsVo;
    }
}
