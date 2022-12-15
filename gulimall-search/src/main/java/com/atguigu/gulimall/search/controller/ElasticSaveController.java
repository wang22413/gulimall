package com.atguigu.gulimall.search.controller;

import com.atguigu.common.exception.BizCodeEnum;
import com.atguigu.common.to.es.SkuEsModel;
import com.atguigu.common.utils.R;
import com.atguigu.gulimall.search.service.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author Mrwsn
 * @create 2022/12/14 22:14
 */
@Slf4j
@RequestMapping("elastic/save")
@RestController
public class ElasticSaveController {

    @Autowired
    ProductSaveService saveService;

    @PostMapping("/product")
    public R productStatusUp(@RequestBody List<SkuEsModel> esModels) {
        Boolean aBoolean = null;
        try {
            aBoolean = saveService.productStatusUp(esModels);
        } catch (IOException e) {
            log.error("ProductSaveService商品上架错误:"+e);
            return R.error(BizCodeEnum.PEODUCT_UP_EXCEPTION.getCode(),BizCodeEnum.PEODUCT_UP_EXCEPTION.getMsg());
        }
        if (!aBoolean) {
            return R.ok();
        } else {
            return R.error(BizCodeEnum.PEODUCT_UP_EXCEPTION.getCode(),BizCodeEnum.PEODUCT_UP_EXCEPTION.getMsg());

        }
    }

}
