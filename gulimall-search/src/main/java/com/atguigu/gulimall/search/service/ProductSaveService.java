package com.atguigu.gulimall.search.service;

import com.atguigu.common.to.es.SkuEsModel;
import com.fasterxml.jackson.databind.node.BooleanNode;

import java.io.IOException;
import java.util.List;

/**
 * @author Mrwsn
 * @create 2022/12/14 22:17
 */
public interface ProductSaveService {
    Boolean productStatusUp(List<SkuEsModel> esModels) throws IOException;
}
