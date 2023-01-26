package com.atguigu.common.to.mq;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Mrwsn
 * @createTime 2023/1/26 21:23
 */
@Data
public class SeckillOrderTo {

    private String orderSn; //订单号
    private Long promotionSessionId;//活动场次id
    private Long skuId; //商品id
    private BigDecimal seckillPrice; //秒杀价格
    private Integer num; //购买数量
    private Long memberId; //会员id

    //sku详细信息
//    private SkuInfoVo skuInfo;

}
