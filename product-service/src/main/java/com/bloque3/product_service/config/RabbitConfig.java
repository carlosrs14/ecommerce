package com.bloque3.product_service.config;

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
    public static final String Q_INVENTORY_COMMANDS = "product.inventory-commands";

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
    Queue inventoryCommandsQueue() {
        return new Queue(Q_INVENTORY_COMMANDS, true);
    }

    @Bean
    Binding inventoryCommandsBinding() {
        return BindingBuilder.bind(inventoryCommandsQueue()).to(exchange()).with("cmd.reserve-inventory");
    }
}
