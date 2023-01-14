package com.atguigu.gulimall.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Mrwsn
 * @createTime 2023/1/14 16:48
 */
@Data
public class FareVo {
    private MemberAddressVo address;
    private BigDecimal fare;
}
