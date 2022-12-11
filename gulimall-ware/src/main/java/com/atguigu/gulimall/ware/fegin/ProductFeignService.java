package com.atguigu.gulimall.ware.fegin;

import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mrwsn
 * @create 2022/12/11 21:12
 */

@FeignClient("gulimall-product")
public interface ProductFeignService {

    @RequestMapping("/product/spuinfo/info/{id}")
    R info(@PathVariable("id") Long id);

}
