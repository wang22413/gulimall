package com.atguigu.gulimall.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 1.如何使用nacos作为配置中心统一管理配置
 * 1)引入依赖
 *  <dependency>
 *      <groupId>com.alibaba.cloud</groupId>
 *      <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
 *  </dependency>
 *  2)创建一个bootstrap.properties
 *      spring.application.name=gulimall-coupon
 *      spring.cloud.nacos.config.server-addr=127.0.0.1:8848
 *  3）需要给配置中心默认添加一个叫 数据集(Data Id) gulimall-coupon.properties.默认规则，应用名.properties
 *  4)给应用名.properties 添加任何配置
 *  5)动态获取配置
 *      @RefreshScope :动态获取并刷新配置
 *      @Value("${配置项的名}") 获取配置
 *      如果bootstrap和配置中都配置的项，先用配置中心的项
 *
 *  2.细节
 *  1）命名空间：配置隔离
 *      默认：public(保留空间)；默认新增的所有配置都在public空间
 *      1.开发，测试，生产：利用命名开机来做环境隔离
 *          注意：再bootstrap。properties；配置上使用哪个命名开机下的配置
 *          spring.cloud.nacos.config.namespace=78f04c7a-9203-454e-ad18-aa8af9521340
 *      2.每一个微服务之间互相隔离配置，每一个微服务都创建自己的命名空间，只加载自己命名空间下的所有配置
 *  2）配置集：所有的配置的集合
 *  3）配置集ID：类似文件名
 *      Data ID:类似文件名
 *  4）配置分组
 *      默认所有的配置集搜属于：DEFAULT_GROUP
 *      spring.cloud.nacos.config.group=[Group]
 */

@EnableDiscoveryClient
@SpringBootApplication
public class GulimallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallCouponApplication.class, args);
    }

}
