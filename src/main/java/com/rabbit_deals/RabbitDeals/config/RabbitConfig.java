package com.rabbit_deals.RabbitDeals.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    public static final String QUEUE_ACA = "academia_queue";
    public static final String QUEUE_TEC = "tecnologia_queue";

    @Bean
    public Queue academiaQueue() {
        return new Queue(QUEUE_ACA, true);
    }

    @Bean
    public Queue tecnologiaQueue() {
        return new Queue(QUEUE_TEC, true);
    }
}