package com.atguigu.gulimall.ware.vo;

import lombok.Data;

/**
 * @author Mrwsn
 * @create 2022/12/11 20:02
 */
@Data
public class PurchaseItemDoneVo {

    private Long itemId;
    private Integer status;
    private String reason;

}
