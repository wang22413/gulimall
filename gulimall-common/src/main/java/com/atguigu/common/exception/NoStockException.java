package com.atguigu.common.exception;

/**
 * @author Mrwsn
 * @createTime 2023/1/15 22:01
 */
public class NoStockException extends RuntimeException {
    private String skuName;

    public NoStockException() {
        super("商品没有足够的库存了");
    }
    public NoStockException(String skuName) {
        super("商品:"+skuName+";没有足够的库存了");
    }

    public String getSkuId() {
        return skuName;
    }

    public void setSkuId(String skuName) {
        this.skuName = skuName;
    }
}
