package com.bloque3.order_service.listeners;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.bloque3.order_service.common.messages.InventoryRejectedEvent;
import com.bloque3.order_service.common.messages.PaymentCompletedEvent;
import com.bloque3.order_service.common.messages.PaymentFailedEvent;
import com.bloque3.order_service.config.RabbitConfig;
import com.bloque3.order_service.services.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventListener {

    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitConfig.Q_INVENTORY_EVENTS)
    public void onInventoryEvent(Message message) {
        try {
            String json = new String(message.getBody());
            log.info("Received inventory event: {}", json);
            String routingKey = message.getMessageProperties().getReceivedRoutingKey();
            if ("ev.inventory-rejected".equals(routingKey)) {
                InventoryRejectedEvent rejected = objectMapper.readValue(json, InventoryRejectedEvent.class);
                orderService.reject(rejected.orderId(), rejected.reason()).subscribe();
            }
        } catch (Exception e) {
            log.error("Error processing inventory event", e);
        }
    }

    @RabbitListener(queues = RabbitConfig.Q_PAYMENT_EVENTS)
    public void onPaymentEvent(Message message) {
        try {
            String json = new String(message.getBody());
            log.info("Received payment event: {}", json);
            String routingKey = message.getMessageProperties().getReceivedRoutingKey();
            if ("ev.payment-completed".equals(routingKey)) {
                PaymentCompletedEvent completed = objectMapper.readValue(json, PaymentCompletedEvent.class);
                orderService.complete(completed.orderId()).subscribe();
            } else if ("ev.payment-failed".equals(routingKey)) {
                PaymentFailedEvent failed = objectMapper.readValue(json, PaymentFailedEvent.class);
                orderService.cancel(failed.orderId()).subscribe();
            }
        } catch (Exception e) {
            log.error("Error processing payment event", e);
        }
    }
}
