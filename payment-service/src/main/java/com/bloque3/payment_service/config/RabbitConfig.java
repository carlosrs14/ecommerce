package com.bloque3.payment_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE = "ecommerce-exchange";
    public static final String Q_PAYMENT_INVENTORY_EVENTS = "payment.inventory-events";

    @Bean
    MessageConverter jackson2JsonMessageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setAssumeSupportedContentType(false);
        return converter;
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    Queue paymentInventoryEventsQueue() {
        return new Queue(Q_PAYMENT_INVENTORY_EVENTS, true);
    }

    @Bean
    Binding paymentInventoryEventsBinding() {
        return BindingBuilder.bind(paymentInventoryEventsQueue()).to(exchange()).with("ev.inventory-reserved");
    }
}
