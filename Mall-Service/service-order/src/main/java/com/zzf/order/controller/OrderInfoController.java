package com.zzf.order.controller;

import com.github.pagehelper.PageInfo;
import com.zzf.model.dto.order.OrderInfoDto;
import com.zzf.model.entity.order.OrderInfo;
import com.zzf.model.vo.common.Result;
import com.zzf.model.vo.common.ResultCodeEnum;
import com.zzf.model.vo.order.TradeVo;
import com.zzf.order.service.OrderInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zzf
 * @date 2024-01-21
 */
@Tag(name = "订单管理")
@RestController
@RequestMapping(value="/api/order/orderInfo")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @Operation(summary = "确认下单")
    @GetMapping("auth/trade")
    public Result<TradeVo> trade() {
        TradeVo tradeVo = orderInfoService.getTrade();
        return Result.build(tradeVo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "提交订单")
    @PostMapping("auth/submitOrder")
    public Result<Long> submitOrder(@Parameter(name = "orderInfoDto",
            description = "请求参数实体类", required = true) @RequestBody OrderInfoDto orderInfoDto) {
        Long orderId = orderInfoService.submitOrder(orderInfoDto);
        return Result.build(orderId, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "获取订单信息")
    @GetMapping("auth/{orderId}")
    public Result<OrderInfo> getOrderInfo(@Parameter(name = "orderId", description = "订单id", required = true) @PathVariable Long orderId) {
        OrderInfo orderInfo = orderInfoService.getOrderInfo(orderId);
        return Result.build(orderInfo, ResultCodeEnum.SUCCESS);
    }
    @Operation(summary = "立即购买")
    @GetMapping("auth/buy/{skuId}")
    public Result<TradeVo> buy(@Parameter(name = "skuId", description = "商品skuId", required = true) @PathVariable Long skuId) {
        TradeVo tradeVo = orderInfoService.buy(skuId);
        return Result.build(tradeVo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "获取订单分页列表")
    @GetMapping("auth/{page}/{limit}")
    public Result<PageInfo<OrderInfo>> list(
            @Parameter(name = "page", description = "当前页码", required = true)
            @PathVariable Integer page,

            @Parameter(name = "limit", description = "每页记录数", required = true)
            @PathVariable Integer limit,

            @Parameter(name = "orderStatus", description = "订单状态", required = false)
            @RequestParam(required = false, defaultValue = "") Integer orderStatus) {
        PageInfo<OrderInfo> pageInfo = orderInfoService.findOrderInfoPage(page, limit, orderStatus);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    //远程调用：根据订单编号获取订单信息
    @Operation(summary = "获取订单信息")
    @GetMapping("auth/getOrderInfoByOrderNo/{orderNo}")
    public Result<OrderInfo> getOrderInfoByOrderNo(@Parameter(name = "orderId", description = "订单id", required = true) @PathVariable String orderNo) {
        OrderInfo orderInfo = orderInfoService.getByOrderNo(orderNo) ;
        return Result.build(orderInfo, ResultCodeEnum.SUCCESS);
    }

    //远程调用：更新订单状态
    @Operation(summary = "更新订单状态")
    @GetMapping("auth/updateOrderStatusPayed/{orderNo}/{orderStatus}")
    public Result updateOrderStatus(@PathVariable(value = "orderNo") String orderNo , @PathVariable(value = "orderStatus") Integer orderStatus) {
        orderInfoService.updateOrderStatus(orderNo , orderStatus);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }


}
