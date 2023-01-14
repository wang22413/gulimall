package com.atguigu.gulimall.order.vo;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author Mrwsn
 * @createTime 2023/1/14 15:21
 */
@Data
public class OrderSubmitVo {
    private Long addrId;//收货地址
    private String payType;//支付方式
    //无需提交需要购买的商品，去购物车服务获取
    //优惠，发票
    private String orderToken;//防重令牌
    private BigDecimal payPrice;//应付价格
    private String note;//订单备注
    //用户相关信息，直接去session取登录的用户
}
