package com.zzf.cart.service;

import com.zzf.model.entity.cart.CartInfo;

import java.util.List;

/**
 * @author zzf
 * @date 2024-01-19
 */
public interface CartService {
    void addToCart(Long skuId, Integer skuNum);

    List<CartInfo> getCartList();

    void deleteCart(Long skuId);

    void checkCart(Long skuId, Integer isChecked);

    void allCheckCart(Integer isChecked);

    void clearCart();

    List<CartInfo> getAllCkecked();

    void deleteChecked();
}
