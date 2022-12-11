package com.atguigu.gulimall.product.vo;

import lombok.Data;

/**
 * @author Mrwsn
 * @create 2022/12/3 21:02
 */
@Data
public class AttrRespVo extends AttrVo {

    private String catelogName;
    private String groupName;

    private Long[] catelogPath;

}
