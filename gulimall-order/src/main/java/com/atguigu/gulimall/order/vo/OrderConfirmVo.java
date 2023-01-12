package com.atguigu.gulimall.order.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Mrwsn
 * @createTime 2023/1/12 22:04
 */
public class OrderConfirmVo {

    //收货地址列表
    @Getter @Setter
    List<MemberAddressVo> address;

    //所有选中的购物项
    @Getter @Setter
    List<OrderItemVo> items;

    //发票记录...
    //优惠券信息
    @Getter @Setter
    Integer integration;

    //防重令牌
    @Getter @Setter
    String orderToken;

//    BigDecimal total;//订单总额
    public BigDecimal getTotal() {
        BigDecimal sum = new BigDecimal("0");
        if (items != null) {
            for (OrderItemVo item : items) {
                BigDecimal multiply = item.getPrice().multiply(new BigDecimal(item.getCount().toString()));
                sum.add(multiply);
            }
        }
        return sum;
    }

//    BigDecimal payPrice;//应付价格
    public BigDecimal getPayPrice() {
        BigDecimal sum = new BigDecimal("0");
        if (items != null) {
            for (OrderItemVo item : items) {
                BigDecimal multiply = item.getPrice().multiply(new BigDecimal(item.getCount().toString()));
                sum.add(multiply);
            }
        }
        return sum;
    }
}
