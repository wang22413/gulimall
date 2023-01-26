package com.atguigu.gulimall.order.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mrwsn
 * @createTime 2023/1/17 20:36
 */
@Configuration
public class MyMQConfig {

    /**
     * 容器中的 Binding、Queue、Exchange都会自动创建（RabbitMQ没有的情况）
     */

    @Bean
    public Queue orderDelayQueue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange","order-event-exchange");
        arguments.put("x-dead-letter-routing-key","order.release.order");
        arguments.put("x-message-ttl",60000);
        Queue queue = new Queue("order.delay.queue", true, false, false,arguments);
        return queue;
    }

    @Bean
    public Queue orderReleaseOrderQueue() {
        Queue queue = new Queue("order.release.order.queue");
        return queue;
    }

    @Bean
    public Exchange orderEventExchange() {
        TopicExchange topicExchange = new TopicExchange("order-event-exchange",true,false);
        return topicExchange;
    }

    @Bean
    public Binding orderCreateOrderBinding() {
        return new Binding("order.delay.queue",
                Binding.DestinationType.QUEUE,
                "order-event-exchange",
                "order.create.order",null);
    }

    @Bean
    public Binding orderReleaseOrderBinding() {
        return new Binding("order.release.order.queue",
                Binding.DestinationType.QUEUE,
                "order-event-exchange",
                "order.release.order",null);
    }

    /**
     * 订单释放和库存释放进行绑定
     * @return
     */
    @Bean
    public Binding orderReleaseOtherBinding() {
        return new Binding("stock.release.stock.queue",
                Binding.DestinationType.QUEUE,
                "order-event-exchange",
                "order.release.other.#",null);
    }

    @Bean
    public Queue orderSeckillOrderQueue() {
        return new Queue("stock.seckill.order.queue",true,false,false);
    }

    @Bean
    public Binding orderSeckillOrderQueueBinding() {
        return new Binding("stock.seckill.order.queue",
                Binding.DestinationType.QUEUE,
                "order-event-exchange",
                "order.seckill.order",null);
    }

}
