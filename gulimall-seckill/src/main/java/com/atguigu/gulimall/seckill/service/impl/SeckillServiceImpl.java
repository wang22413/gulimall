package com.atguigu.gulimall.seckill.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.atguigu.common.to.mq.SeckillOrderTo;
import com.atguigu.common.utils.R;
import com.atguigu.common.vo.MemberRespVo;
import com.atguigu.gulimall.seckill.feign.CouponFeignService;
import com.atguigu.gulimall.seckill.feign.ProductFeignService;
import com.atguigu.gulimall.seckill.interceptor.LoginUserInterceptor;
import com.atguigu.gulimall.seckill.service.SeckillService;
import com.atguigu.gulimall.seckill.to.SeckillSkuRedisTo;
import com.atguigu.gulimall.seckill.vo.SeckillSessionWhitSkus;
import com.atguigu.gulimall.seckill.vo.SkuInfoVo;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Mrwsn
 * @createTime 2023/1/24 15:09
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    CouponFeignService couponFeignService;
    @Autowired
    ProductFeignService productFeignService;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    RedissonClient redissonClient;

    private final String SESSION_CACHE_PREFIX = "seckill:session:";
    private final String SKUKILL_CACHE_PREFIX = "seckill:skus";
    private final String SKU_STOCK_SEMAPHORE = "seckill:stock:"; // + 商品随机码

    @Override
    public void uploadSeckillSkuLatest3Days() {
        //1、扫描最近3天参与秒杀的活动
        R session = couponFeignService.getLatest3DaySession();
        if (session.getCode() == 0) {
            //上架商品
            List<SeckillSessionWhitSkus> sessionData = session.getData(new TypeReference<List<SeckillSessionWhitSkus>>() {
            });
            //缓存到redis
            //1、缓存活动信息
            saveSessionInfos(sessionData);
            //2、缓存活动的相关商品信息
            saveSessionSkuInfos(sessionData);

        }
    }

    //返回当前时间可以参与的秒杀商品信息
    @Override
    public List<SeckillSkuRedisTo> getCurrentSeckillSkus() {
        long now = new Date().getTime();
        Set<String> keys = redisTemplate.keys(SESSION_CACHE_PREFIX + "*");
        for (String key : keys) {
            String replace = key.replace(SESSION_CACHE_PREFIX, "");
            String[] s = replace.split("_");
            long start = Long.parseLong(s[0]);
            long end = Long.parseLong(s[1]);
            if (now >= start && now <=end) {
                //获取这个秒杀场次需要的所有商品信息
                List<String> range = redisTemplate.opsForList().range(key, -100, 100);
                BoundHashOperations<String, String, String> hashOps = redisTemplate.boundHashOps(SKUKILL_CACHE_PREFIX);
                List<String> list = hashOps.multiGet(range);
                if (list != null) {
                    List<SeckillSkuRedisTo> collect = list.stream().map(item -> {
                        SeckillSkuRedisTo redisTo = JSON.parseObject((String) item, SeckillSkuRedisTo.class);
//                        redisTo.setRandomCode(null); 当前秒杀开始了就需要随机码
                        return redisTo;
                    }).collect(Collectors.toList());
                    return collect;
                }
                break;
            }
        }
        return null;
    }

    @Override
    public SeckillSkuRedisTo getSkuSeckillInfo(Long skuId) {
        //找到所有需要秒杀的key
        BoundHashOperations<String, String, String> hashOps = redisTemplate.boundHashOps(SKUKILL_CACHE_PREFIX);
        Set<String> keys = hashOps.keys();

        if (keys != null && keys.size() > 0) {
            String regx = "\\d_"+skuId;
            for (String key : keys) {
                if (Pattern.matches(regx,key)) {
                    String json = hashOps.get(key);
                    SeckillSkuRedisTo redisTo = JSON.parseObject(json, SeckillSkuRedisTo.class);
                    //随机码
                    long time = new Date().getTime();
                    Long startTime = redisTo.getStartTime();
                    Long endTime = redisTo.getEndTime();
                    if (time >= startTime && time <= endTime) {
                    }else {
                        redisTo.setRandomCode(null);
                    }
                    return redisTo;
                }
            }
        }
        return null;
    }

    @Override
    public String kill(String killId, String key, Integer num) {
        MemberRespVo respVo = LoginUserInterceptor.loginUser.get();
        BoundHashOperations<String, String, String> ops = redisTemplate.boundHashOps(SKUKILL_CACHE_PREFIX);
        String json = ops.get(killId);
        if (StringUtils.isEmpty(json)) {
            return null;
        } else {
            SeckillSkuRedisTo redisTo = JSON.parseObject(json, SeckillSkuRedisTo.class);
            //校验合法性
            Long startTime = redisTo.getStartTime();
            Long endTime = redisTo.getEndTime();
            long time = new Date().getTime();

            Long ttl = endTime - time;
            if (time >= startTime && time <= endTime) {
                //校验随机码和是商品id
                String randomCode = redisTo.getRandomCode();
                String skuId = redisTo.getPromotionSessionId() + "_" + redisTo.getSkuId();
                if (randomCode.equals(key) && killId.equals(skuId)) {
                    if (num <= redisTo.getStartTime()) {
                        String redisKey = respVo.getId() + "_" + skuId;
                        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(redisKey, num.toString(), ttl, TimeUnit.MILLISECONDS);
                        if (aBoolean) {
                            //占位成功，可以购买
                            RSemaphore semaphore = redissonClient.getSemaphore(SKU_STOCK_SEMAPHORE + randomCode);

                            //秒杀成功
                            boolean b = semaphore.tryAcquire(num);
                            if (b) {
                                //快速下单，发给MQ
                                String timeId = IdWorker.getTimeId();
                                SeckillOrderTo orderTo = new SeckillOrderTo();
                                orderTo.setOrderSn(timeId);
                                orderTo.setMemberId(respVo.getId());
                                orderTo.setNum(num);
                                orderTo.setPromotionSessionId(redisTo.getPromotionSessionId());
                                orderTo.setSkuId(redisTo.getSkuId());
                                orderTo.setSeckillPrice(redisTo.getSeckillPrice());

                                rabbitTemplate.convertAndSend("order-event-exchange", "order.seckill.order", orderTo);
                                return timeId;
                            }
                            return null;
                        }
                    }
                } else {
                    return null;
                }
            }
            return null;
        }
    }

    private void saveSessionInfos(List<SeckillSessionWhitSkus> sessions) {
        sessions.stream().forEach(session->{
            Long startTime = session.getStartTime().getTime();
            Long endTime = session.getEndTime().getTime();
            String key = startTime + "_" + endTime;
            //缓存活动信息
            Boolean hasKey = redisTemplate.hasKey(SESSION_CACHE_PREFIX + key);
            if (!hasKey) {
                List<String> collect = session.getRelationSkus().stream().map(item ->
                        item.getPromotionSessionId()+"_"+item.getSkuId().toString()).collect(Collectors.toList());
                redisTemplate.opsForList().leftPushAll(SESSION_CACHE_PREFIX + key,collect);
            }

        });
    }

    private void saveSessionSkuInfos(List<SeckillSessionWhitSkus> sessions) {
        BoundHashOperations<String, Object, Object> ops = redisTemplate.boundHashOps(SKUKILL_CACHE_PREFIX);
        sessions.stream().forEach(session->{
            session.getRelationSkus().stream().forEach(seckillSkuVo -> {
                //随机码
                String token = UUID.randomUUID().toString().replace("-", "");

                if (!ops.hasKey(seckillSkuVo.getPromotionSessionId().toString()+"_"+seckillSkuVo.getSkuId().toString())) {
                    //缓存商品
                    SeckillSkuRedisTo redisTo = new SeckillSkuRedisTo();
                    //sku基本信息
                    R r = productFeignService.getSkuInfo(seckillSkuVo.getSkuId());
                    if (r.getCode() == 0) {
                        SkuInfoVo skuInfo = r.getData("skuInfo", new TypeReference<SkuInfoVo>() {
                        });
                        redisTo.setSkuInfo(skuInfo);
                    }
                    //sku秒杀信息
                    BeanUtils.copyProperties(seckillSkuVo,redisTo);

                    //设置当前商品的秒杀时间信息
                    redisTo.setStartTime(session.getStartTime().getTime());
                    redisTo.setEndTime(session.getEndTime().getTime());

                    redisTo.setRandomCode(token);

                    String s = JSON.toJSONString(redisTo);
                    ops.put(seckillSkuVo.getPromotionSessionId().toString()+"_"+seckillSkuVo.getSkuId().toString(),s);

                    //使用库存作为分布式的信号量 限流
                    RSemaphore semaphore = redissonClient.getSemaphore(SKU_STOCK_SEMAPHORE + token);
                    //商品可以秒杀的数量作为信号量
                    semaphore.trySetPermits(seckillSkuVo.getSeckillCount().intValue());
                }
            });
        });
    }
}
