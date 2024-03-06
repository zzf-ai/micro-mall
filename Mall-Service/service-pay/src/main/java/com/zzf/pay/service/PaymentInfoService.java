package com.zzf.pay.service;

import com.zzf.model.entity.pay.PaymentInfo;

import java.util.Map;

/**
 * @author zzf
 * @date 2024-01-22
 */
public interface PaymentInfoService {
    PaymentInfo savePaymentInfo(String orderNo);

    void updatePaymentStatus(Map<String, String> paramMap, Integer payType);
}
