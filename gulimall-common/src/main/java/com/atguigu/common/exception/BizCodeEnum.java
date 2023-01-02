package com.atguigu.common.exception;

/**
 * @author Mrwsn
 * @create 2022/12/2 16:57
 */
public enum BizCodeEnum {
    UNKNOW_EXCEPTION(10000,"系统未知异常"),
    VAILD_EXCEPTION(10001,"参数格式校验错误"),
    SMS_CODE_EXCEPTION(10002,"验证码获取频率太高，稍后再试"),
    PEODUCT_UP_EXCEPTION(11000,"商品上架异常");

    private int code;
    private String msg;
    BizCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
