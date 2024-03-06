package com.zzf.order.mapper;

import com.zzf.model.entity.order.OrderLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zzf
 * @date 2024-01-22
 */
@Mapper
public interface OrderLogMapper {
    void save(OrderLog orderLog);
}
