package com.atguigu.gulimall.coupon.dao;

import com.atguigu.gulimall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author Mrwsn
 * @email Mrwsn@gmail.com
 * @date 2022-11-27 17:20:07
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
