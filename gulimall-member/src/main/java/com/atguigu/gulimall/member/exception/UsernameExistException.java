package com.atguigu.gulimall.member.exception;

/**
 * @author Mrwsn
 * @createTime 2023/1/4 13:44
 */
public class UsernameExistException extends RuntimeException {

    public UsernameExistException() {
        super("用户名已存在");
    }
}
