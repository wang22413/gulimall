package com.atguigu.gulimall.member.exception;

/**
 * @author Mrwsn
 * @createTime 2023/1/4 13:44
 */
public class PhoneExistException extends RuntimeException {
    public PhoneExistException() {
        super("手机号已存在");
    }
}
