package com.atguigu.gulimall.search.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Mrwsn
 * @createTime 2022/12/21 22:04
 */
@Data
public class SearchParam {

    private String keyword;
    private Long catalog3Id;
    private String sort;
    private Integer hasStock = 1;
    private String skuPrice;
    private List<Long> brandId;
    private List<String> attrs;
    private Integer pageNum = 1;
}
