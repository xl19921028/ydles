package com.ydles.canal.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xialiang
 * @date 2/19/22
 */
@Configuration
public class RabbitMQConfig {
    //定义队列名称
    public static final String AD_UPDATE_QUEUE="ad_update_queue";
    @Bean
    public Queue queue(){
        return new Queue(AD_UPDATE_QUEUE,true);
    }

    //上架交换机名称
    public static final String GOODS_UP_EXCHANGE = "goods_up_exchange";

    //定义上架队列名称
    public static final String SEARCH_ADD_QUEUE = "search_add_queue";

    //定义上架队列
    @Bean(SEARCH_ADD_QUEUE)
    public Queue SEARCH_ADD_QUEUE(){
        return new Queue(SEARCH_ADD_QUEUE);
    }

    //定义上架交换机
    @Bean(GOODS_UP_EXCHANGE)
    public Exchange GOODS_UP_EXCHANGE(){
        return ExchangeBuilder.fanoutExchange(GOODS_UP_EXCHANGE).durable(true)
                .build();
    }
    //上架队列和上架交换机绑定关系构建，@Qualifier(SEARCH_ADD_QUEUE)，从容器中拿出对象SEARCH_ADD_QUEUE
    @Bean
    public Binding GOODS_UP_EXCHANGE_SEARCH_ADD_QUEUE(
            @Qualifier(SEARCH_ADD_QUEUE)Queue queue,
            @Qualifier(GOODS_UP_EXCHANGE)Exchange exchange ){
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    } }