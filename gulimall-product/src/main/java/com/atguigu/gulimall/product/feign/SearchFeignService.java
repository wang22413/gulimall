package com.atguigu.gulimall.product.feign;

import com.atguigu.common.to.es.SkuEsModel;
import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Mrwsn
 * @create 2022/12/14 22:58
 */
@FeignClient("gulimall-search")
public interface SearchFeignService {

    @PostMapping("/elastic/save/product")
    R productStatusUp(@RequestBody List<SkuEsModel> esModels);

}
