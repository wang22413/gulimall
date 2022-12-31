package com.atguigu.gulimall.product.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Mrwsn
 * @createTime 2022/12/31 23:06
 */
@Data
@ToString
public class SpuItemAttrGroupVo {
    private String groupName;
    private List<Attr> attrs;
}
