package com.zzf.pay.service.impl;

import com.alibaba.fastjson.JSON;
import com.zzf.feign.order.OrderFeignClient;
import com.zzf.feign.product.ProductFeignClient;
import com.zzf.model.dto.product.SkuSaleDto;
import com.zzf.model.entity.order.OrderInfo;
import com.zzf.model.entity.order.OrderItem;
import com.zzf.model.entity.pay.PaymentInfo;
import com.zzf.pay.mapper.PaymentInfoMapper;
import com.zzf.pay.service.PaymentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zzf
 * @date 2024-01-22
 */
@Service
public class PaymentInfoServiceImpl implements PaymentInfoService {

    @Autowired
    private PaymentInfoMapper paymentInfoMapper;

    @Autowired
    private OrderFeignClient orderFeignClient;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public PaymentInfo savePaymentInfo(String orderNo) {
        //根据订单号查询支付信息数据
        PaymentInfo paymentInfo = paymentInfoMapper.getByOrderNo(orderNo);
        //判断支付记录是否存在
        if(null == paymentInfo){
            //远程调用获取订单信息
            OrderInfo orderInfo = orderFeignClient.getOrderInfoByOrderNo(orderNo).getData();
            //把orderinfo获取数据封装到paymentInfo里面
            paymentInfo = new PaymentInfo();
            paymentInfo.setUserId(orderInfo.getUserId());
            paymentInfo.setPayType(orderInfo.getPayType());
            String content = "";
            for(OrderItem item : orderInfo.getOrderItemList()) {
                content += item.getSkuName() + " ";
            }
            paymentInfo.setContent(content);
            paymentInfo.setAmount(orderInfo.getTotalAmount());
            paymentInfo.setOrderNo(orderNo);
            paymentInfo.setPaymentStatus(0);
            //添加
            paymentInfoMapper.save(paymentInfo);
        }
        return paymentInfo;
    }

    @Transactional
    @Override
    public void updatePaymentStatus(Map<String, String> map, Integer payType) {
        PaymentInfo paymentInfo = paymentInfoMapper.getByOrderNo(map.get("out_trade_no"));
        if(paymentInfo.getPaymentStatus() == 1){
            return;
        }
        //更新支付信息
        paymentInfo.setPaymentStatus(1);
        paymentInfo.setOutTradeNo(map.get("trade_no"));
        paymentInfo.setCallbackTime(new Date());
        paymentInfo.setCallbackContent(JSON.toJSONString(map));
        paymentInfoMapper.updateById(paymentInfo);

        //更新订单支付状态
        orderFeignClient.updateOrderStatus(paymentInfo.getOrderNo() , payType);

        //更新商品销量
        OrderInfo orderInfo = orderFeignClient.getOrderInfoByOrderNo(paymentInfo.getOrderNo()).getData();
        List<SkuSaleDto> skuSaleDtoList = orderInfo.getOrderItemList().stream().map(orderItem -> {
            SkuSaleDto skuSaleDto = new SkuSaleDto();
            skuSaleDto.setSkuId(orderItem.getSkuId());
            skuSaleDto.setNum(orderItem.getSkuNum());
            return skuSaleDto;
        }).collect(Collectors.toList());

        productFeignClient.updateSkuSaleNum(skuSaleDtoList);
    }
}
