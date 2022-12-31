/**
  * Copyright 2022 bejson.com 
  */
package com.atguigu.gulimall.product.vo;

/**
 * Auto-generated: 2022-12-05 23:37:30
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Attr {

    private int attrId;
    private String attrName;
    private String attrValue;
    public void setAttrId(int attrId) {
         this.attrId = attrId;
     }
     public int getAttrId() {
         return attrId;
     }

    public void setAttrName(String attrName) {
         this.attrName = attrName;
     }
     public String getAttrName() {
         return attrName;
     }

    public void setAttrValue(String attrValue) {
         this.attrValue = attrValue;
     }
     public String getAttrValue() {
         return attrValue;
     }

    @Override
    public String toString() {
        return "Attr{" +
                "attrId=" + attrId +
                ", attrName='" + attrName + '\'' +
                ", attrValue='" + attrValue + '\'' +
                '}';
    }
}