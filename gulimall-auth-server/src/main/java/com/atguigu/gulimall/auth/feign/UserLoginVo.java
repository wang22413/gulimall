package com.atguigu.gulimall.auth.feign;

import lombok.Data;

/**
 * @author Mrwsn
 * @createTime 2023/1/4 15:01
 */
@Data
public class UserLoginVo {
    private String loginacct;
    private String password;
}
