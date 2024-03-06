package com.zzf.manager.task;

import cn.hutool.core.date.DateUtil;
import com.zzf.manager.mapper.OrderInfoMapper;
import com.zzf.manager.mapper.OrderStatisticsMapper;
import com.zzf.model.entity.order.OrderStatistics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zzf
 * @date 2024-01-13
 */
@Component
@Slf4j
public class OrderStatisticsTask {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;

    //定时任务：每天凌晨2点统计前一天的订单数据
    @Scheduled(cron = "0 0 2 * * ?")
    public void orderDataStatistics(){
        //前一天日期
        String createTime = DateUtil.offsetDay(new Date(), -1).toString(new SimpleDateFormat("yyyy-MM-dd"));
        //根据日期查询订单数据统计
        OrderStatistics orderStatistics = orderInfoMapper.selectOrderStatistics(createTime);
        //把订单数据统计插入统计表
        if(orderStatistics != null){
            orderStatisticsMapper.insert(orderStatistics);
        }
    }
}
