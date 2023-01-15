package com.atguigu.gulimall.ware.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Mrwsn
 * @createTime 2023/1/12 22:09
 */
@Data
public class OrderItemVo {

    private Long skuId;
    private String title;
    private String image;
    private List<String> skuAttr;
    private BigDecimal price;
    private Integer count;
    private BigDecimal totalPrice;

    // TODO 查询商品状态 重量
    private Boolean hasStock = true;
    private BigDecimal weight;

}
