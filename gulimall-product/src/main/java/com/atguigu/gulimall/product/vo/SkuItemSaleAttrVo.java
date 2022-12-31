package com.atguigu.gulimall.product.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Mrwsn
 * @createTime 2022/12/31 23:09
 */
@Data
@ToString
public class SkuItemSaleAttrVo {
    private Long attrId;
    private String attrName;
    private List<String> attrValues;
}
