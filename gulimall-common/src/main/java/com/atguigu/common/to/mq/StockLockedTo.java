package com.atguigu.common.to.mq;

import lombok.Data;

import java.util.List;

/**
 * @author Mrwsn
 * @createTime 2023/1/18 22:58
 */
@Data
public class StockLockedTo {
    private Long id;//库存工资单的id
    private StockDetailTo detailTo ;//工作详情的所有id
}
