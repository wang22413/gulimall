package com.atguigu.gulimall.order.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Mrwsn
 * @createTime 2023/1/11 22:56
 */
@Controller
public class HelloController {
    /**
     * @PathVariable 取路径中{}里面的
     * @RequestParam 取路径中?后面 K=V 中的 K
     * @param page
     * @return
     */
    @GetMapping("/{page}.html")
    public String listPage(@PathVariable("page") String page) {
        return page;
    }

}
