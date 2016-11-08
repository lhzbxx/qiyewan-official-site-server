package com.qiyewan.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * Rabbit配置
 */

@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

    @Bean
    public Queue captchaQueue() {
        return new Queue("sms-queue");
    }

    @Bean
    public Queue orderNotifyQueue() { return new Queue("order-notify-queue"); }

    @Bean
    public Queue orderTimeoutQueue() { return new Queue("order-timeout-queue"); }

    @Bean
    public Queue loginHistoryRecord() { return new Queue("login-history-record-queue"); }

    @Bean
    CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange("order-timeout-exchange", "x-delayed-message", true, false, args);
    }

    @Bean
    Binding binding(Queue orderTimeoutQueue, Exchange delayExchange) {
        return BindingBuilder.bind(orderTimeoutQueue).to(delayExchange).with("order-timeout-queue").noargs();
    }

}
