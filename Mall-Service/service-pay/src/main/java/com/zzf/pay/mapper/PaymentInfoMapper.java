package com.zzf.pay.mapper;

import com.zzf.model.entity.pay.PaymentInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zzf
 * @date 2024-01-22
 */
@Mapper
public interface PaymentInfoMapper {

    PaymentInfo getByOrderNo(String orderNo);

    void save(PaymentInfo paymentInfo);

    void updateById(PaymentInfo paymentInfo);
}
