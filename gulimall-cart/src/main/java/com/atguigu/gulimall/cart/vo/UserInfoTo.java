package com.atguigu.gulimall.cart.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @author Mrwsn
 * @createTime 2023/1/9 17:30
 */
@ToString
@Data
public class UserInfoTo {

    private Long userId;
    private String userKey;

    private Boolean tempUser = false;

}
