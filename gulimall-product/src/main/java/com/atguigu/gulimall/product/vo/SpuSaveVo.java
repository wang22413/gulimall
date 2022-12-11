/**
  * Copyright 2022 bejson.com 
  */
package com.atguigu.gulimall.product.vo;
import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2022-12-05 23:37:30
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class SpuSaveVo {

    private String spuName;
    private String spuDescription;
    private Long catalogId;
    private Long brandId;
    private int weight;
    private int publishStatus;
    private List<String> decript;
    private List<String> images;
    private Bounds bounds;
    private List<BaseAttrs> baseAttrs;
    private List<Skus> skus;

}