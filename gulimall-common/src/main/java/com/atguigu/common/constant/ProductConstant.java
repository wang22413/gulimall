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

    public enum StatusMenu{
        NEW_SPU(0,"新建"),SPU_UP(1,"商品上架"),SPU_DOWN(2,"商品下架");

        private int code;
        private String msg;

        StatusMenu(int code,String msg) {
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
