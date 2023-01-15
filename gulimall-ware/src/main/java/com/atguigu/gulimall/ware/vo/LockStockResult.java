package com.atguigu.gulimall.ware.vo;

import lombok.Data;

/**
 * @author Mrwsn
 * @createTime 2023/1/15 21:38
 */
@Data
public class LockStockResult {
    private Long skuId;
    private Integer num;
    private Boolean locked;
}
