package com.atguigu.gulimall.order.vo;

import com.atguigu.gulimall.order.entity.OrderEntity;
import lombok.Data;

/**
 * @author Mrwsn
 * @createTime 2023/1/14 16:15
 */
@Data
public class SubmitOrderResponseVo {
    private OrderEntity order;
    private Integer code;
}
