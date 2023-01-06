package com.atguigu.gulimall.auth.vo;

import lombok.Data;

/**
 * @author Mrwsn
 * @createTime 2023/1/6 16:41
 */
@Data
public class SocialUserVo {

    private String access_token;
    private String remind_in;
    private long expires_in;
    private String uid;
    private String isRealName;

}
