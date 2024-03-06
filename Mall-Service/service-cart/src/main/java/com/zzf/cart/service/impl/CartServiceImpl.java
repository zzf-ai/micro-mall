package com.zzf.cart.service.impl;

import com.alibaba.fastjson.JSON;
import com.zzf.cart.service.CartService;
import com.zzf.common.constant.Constants;
import com.zzf.feign.product.ProductFeignClient;
import com.zzf.model.entity.cart.CartInfo;
import com.zzf.model.entity.product.ProductSku;
import com.zzf.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zzf
 * @date 2024-01-19
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisTemplate<String , String> redisTemplate;

    @Autowired
    private ProductFeignClient productFeignClient;

    private String getCartKey(Long userId) {
        //定义key user:cart:userId
        return Constants.USER_CART_KEY + userId;
    }

    @Override
    public void addToCart(Long skuId, Integer skuNum) {
        Long userId = AuthContextUtil.getUserInfo().getId();
        //构建hash类型的key
        String cartKey = getCartKey(userId);
        //从redis中获取购物车数据，用大key用户id和小key skuId获取
        Object cartInfoObj = redisTemplate.opsForHash().get(cartKey, String.valueOf(skuId));
        CartInfo cartInfo = null;
        // 如果购物车存在添加的商品，那么就将商品数量相加
        if(cartInfoObj != null){
            cartInfo = JSON.parseObject(cartInfoObj.toString(), CartInfo.class);
            cartInfo.setSkuNum(cartInfo.getSkuNum() + skuNum);
            //标记默认选中
            cartInfo.setIsChecked(1);
            cartInfo.setUpdateTime(new Date());
        }else{
            cartInfo = new CartInfo();
            ProductSku productSku = productFeignClient.getBySkuId(skuId);
            cartInfo.setCartPrice(productSku.getSalePrice());
            cartInfo.setSkuNum(skuNum);
            cartInfo.setSkuId(skuId);
            cartInfo.setUserId(userId);
            cartInfo.setImgUrl(productSku.getThumbImg());
            cartInfo.setSkuName(productSku.getSkuName());
            cartInfo.setIsChecked(1);
            cartInfo.setCreateTime(new Date());
            cartInfo.setUpdateTime(new Date());
        }
        //将商品数据存储到购物车中
        redisTemplate.opsForHash().put(cartKey, String.valueOf(skuId), JSON.toJSONString(cartInfo));
    }

    @Override
    public List<CartInfo> getCartList() {
        //获取当前登录的用户信息
        Long userId = AuthContextUtil.getUserInfo().getId();
        //构建大key
        String cartKey = this.getCartKey(userId);
        //直接利用大key获取小map数据值列表
        List<Object> cartInfoList = redisTemplate.opsForHash().values(cartKey);
        if(!CollectionUtils.isEmpty(cartInfoList)){
            //stream流遍历将json对象转换成cartInfo对象
            List<CartInfo> infoList = cartInfoList.stream().map(cartInfoJSON -> JSON.parseObject(cartInfoJSON.toString(), CartInfo.class))
                    .sorted((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()))
                    .collect(Collectors.toList());
            return infoList;
        }
        return new ArrayList<>();
    }

    @Override
    public void deleteCart(Long skuId) {
        //获取当前登录的用户数据
        Long userId = AuthContextUtil.getUserInfo().getId();
        //构建大key
        String cartKey = this.getCartKey(userId);
        //删除缓存
        redisTemplate.opsForHash().delete(cartKey, String.valueOf(skuId));
    }

    @Override
    public void checkCart(Long skuId, Integer isChecked) {
        // 获取当前登录的用户数据
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);
        //判断key是否包含filed
        Boolean hasKey = redisTemplate.opsForHash().hasKey(cartKey, String.valueOf(skuId));
        if(hasKey){
            String carInfoJSON = redisTemplate.opsForHash().get(cartKey, String.valueOf(skuId)).toString();
            CartInfo cartInfo = JSON.parseObject(carInfoJSON, CartInfo.class);
            cartInfo.setIsChecked(isChecked);
            redisTemplate.opsForHash().put(cartKey, String.valueOf(skuId), cartInfo);
        }
    }

    @Override
    public void allCheckCart(Integer isChecked) {
        // 获取当前登录的用户数据
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);
        // 获取所有的购物项数据
        List<Object> objectList = redisTemplate.opsForHash().values(cartKey);
        if(!CollectionUtils.isEmpty(objectList)){
            objectList.stream().map(cartInfoJSON -> {
                CartInfo cartInfo = JSON.parseObject(cartInfoJSON.toString(), CartInfo.class);
                cartInfo.setIsChecked(isChecked);
                return cartInfo;
            }).forEach(cartInfo -> redisTemplate.opsForHash()
                    .put(cartKey, String.valueOf(cartInfo.getSkuId()), JSON.toJSONString(cartInfo)));
        }
    }

    @Override
    public void clearCart() {
        // 获取当前登录的用户数据
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);
        //删除数据
        redisTemplate.delete(cartKey);
    }

    @Override
    public List<CartInfo> getAllCkecked() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);
        //获取所有购物车数据
        List<Object> objectList = redisTemplate.opsForHash().values(cartKey);
        if(CollectionUtils.isEmpty(objectList)){
            List<CartInfo> cartInfoList = objectList.stream().map(cartInfoJSON -> JSON.parseObject(cartInfoJSON.toString(), CartInfo.class))
                    .filter(cartInfo -> cartInfo.getIsChecked() == 1)
                    .collect(Collectors.toList());
            return cartInfoList;
        }
        return new ArrayList<>();
    }

    @Override
    public void deleteChecked() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);
        //获取所有购物车数据
        List<Object> objectList = redisTemplate.opsForHash().values(cartKey);
        //删除选中商品
        if(!CollectionUtils.isEmpty(objectList)){
            objectList.stream().map(cartInfoJSON -> JSON.parseObject(cartInfoJSON.toString(), CartInfo.class))
                    .filter(cartInfo -> cartInfo.getIsChecked() == 1)
                    .forEach(cartInfo -> redisTemplate.opsForHash().delete(cartKey, String.valueOf(cartInfo.getSkuId())));
        }
    }


}
