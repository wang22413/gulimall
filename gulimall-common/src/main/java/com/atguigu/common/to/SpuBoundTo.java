package com.atguigu.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Mrwsn
 * @create 2022/12/6 21:45
 */
@Data
public class SpuBoundTo {

    private Long spuId;
    private BigDecimal buyBounds;
    private BigDecimal growBounds;

}
