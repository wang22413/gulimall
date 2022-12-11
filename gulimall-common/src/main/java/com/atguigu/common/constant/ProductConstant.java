package com.atguigu.common.constant;

import java.awt.*;

/**
 * @author Mrwsn
 * @create 2022/12/3 22:58
 */
public class ProductConstant {

    public enum AttrMenu{
        ATTR_TYPE_BASE(1,"基本属性"),ATTR_TYPE_SALE(0,"销售属性");

        private int code;
        private String msg;

        AttrMenu(int code,String msg) {
            this.code = code;
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }

        public int getCode() {
            return code;
        }
    }

}
