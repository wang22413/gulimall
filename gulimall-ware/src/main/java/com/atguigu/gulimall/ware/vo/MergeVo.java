package com.atguigu.gulimall.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Mrwsn
 * @create 2022/12/11 16:34
 */
@Data
public class MergeVo {

    private List<Long> items;
    private Long purchaseId;

}
