package com.zzf.manager.mapper;

import com.zzf.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zzf
 * @date 2024-01-13
 */
@Mapper
public interface OrderInfoMapper {
    OrderStatistics selectOrderStatistics(String createTime);
}
