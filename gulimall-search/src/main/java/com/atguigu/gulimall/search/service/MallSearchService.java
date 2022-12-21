package com.atguigu.gulimall.search.service;

import com.atguigu.gulimall.search.vo.SearchParam;
import com.atguigu.gulimall.search.vo.SearchResult;

/**
 * @author Mrwsn
 * @createTime 2022/12/21 22:05
 */
public interface MallSearchService {

    SearchResult search(SearchParam searchParam);
}
