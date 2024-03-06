package com.zzf.feign.cart;

import com.zzf.model.entity.cart.CartInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-21
 */
@FeignClient(value = "service-cart")
public interface CartFeignClient {

    @GetMapping(value = "/api/order/cart/auth/getAllCkecked")
    List<CartInfo> getAllCkecked();

    @GetMapping(value = "/api/order/cart/auth/deleteChecked")
    void deleteChecked();
}
