package com.bloque3.order_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfing {
    public static final String EXCHANGE =  "ecommerce-exchange";
    public static final String Q_INVENTORY_EVENTS = "order.inventory-events";
    public static final String Q_PAYMENT_EVENTS = "order.payment-events";

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    Queue inventoryEventsQueue() {
        return new Queue(Q_INVENTORY_EVENTS, true);
    }

    @Bean
    Queue paymentEventsQueue() {
        return new Queue(Q_PAYMENT_EVENTS, true);
    }

    @Bean
    Binding inventoryEventsBinding() {
        return BindingBuilder.bind(inventoryEventsQueue()).to(exchange()).with("ev.inventory-reserved");
    }

    @Bean
    Binding inventoryRejectedBinding() {
        return BindingBuilder.bind(inventoryEventsQueue()).to(exchange()).with("ev.inventory-rejected");
    }

    @Bean
    Binding paymentEvnetsBinding() {
        return BindingBuilder.bind(paymentEventsQueue()).to(exchange()).with("ev.payment-completed");
    }

    @Bean
    Binding paymentFailedBinding() {
        return BindingBuilder.bind(paymentEventsQueue()).to(exchange()).with("ev.payment-failed");
    }
    
}
