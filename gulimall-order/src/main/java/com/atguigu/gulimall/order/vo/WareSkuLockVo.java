package com.atguigu.gulimall.order.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Mrwsn
 * @createTime 2023/1/15 21:35
 */
@Data
public class WareSkuLockVo {
    private String orderSn;
    private List<OrderItemVo> locks;
}
