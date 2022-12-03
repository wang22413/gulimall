package com.atguigu.gulimall.ware.dao;

import com.atguigu.gulimall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author Mrwsn
 * @email Mrwsn@gmail.com
 * @date 2022-11-27 17:40:04
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}
