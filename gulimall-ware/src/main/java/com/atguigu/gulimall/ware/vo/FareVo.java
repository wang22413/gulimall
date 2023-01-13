package com.atguigu.gulimall.ware.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Mrwsn
 * @createTime 2023/1/13 20:25
 */
@Data
public class FareVo {

    private MemberAddressVo address;
    private BigDecimal fare;

}
