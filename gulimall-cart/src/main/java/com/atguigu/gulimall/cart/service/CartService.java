package com.atguigu.gulimall.cart.service;

import com.atguigu.gulimall.cart.vo.CartItem;

import java.util.concurrent.ExecutionException;

/**
 * @author Mrwsn
 * @createTime 2023/1/9 15:30
 */
public interface CartService {
    CartItem addToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException;
}
