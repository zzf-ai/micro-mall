package com.zzf.pay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.zzf.model.vo.common.Result;
import com.zzf.model.vo.common.ResultCodeEnum;
import com.zzf.pay.properties.AlipayProperties;
import com.zzf.pay.service.AlipayService;
import com.zzf.pay.service.PaymentInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author zzf
 * @date 2024-01-22
 */
@Tag(name = "支付管理")
@RestController
@RequestMapping("/api/order/alipay")
public class AlipayController {

    @Autowired
    private AlipayService alipayService;

    @Autowired
    private AlipayProperties alipayProperties;

    @Autowired
    private PaymentInfoService paymentInfoService;

    @Operation(summary="支付宝下单")
    @GetMapping("submitAlipay/{orderNo}")
    public Result<String> submitAlipay(@Parameter(name = "orderNo", description = "订单号", required = true) @PathVariable(value = "orderNo") String orderNo) {
        String form = alipayService.submitAlipay(orderNo);
        return Result.build(form, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary="支付宝异步回调")
    @RequestMapping("callback/notify")
    public String alipaynotify(@RequestParam Map<String, String> paramMap, HttpServletRequest request){
        //调用SDK验证签名
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(paramMap, alipayProperties.getAlipayPublicKey(), AlipayProperties.charset, AlipayProperties.sign_type);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        //交易状态
        String trade_status = paramMap.get("trade_status");
        /*
        验签成功后，按照支付结果异步通知中的描述，
        对支付结果中的业务内容进行二次校验，
        校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
        */
        if(signVerified){
            if("TRADE_SUCCESS".equals(trade_status) || "TRADE_FINISHED".equals(trade_status)){
                // 正常的支付成功，我们应该更新交易记录状态
                paymentInfoService.updatePaymentStatus(paramMap ,2);
                return "success";
            }
        }else {
            return "failure";
        }
        return "failure";
    }

}
