package com.bloque3.payment_service.listeners;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.bloque3.payment_service.common.messages.InventoryReservedEvent;
import com.bloque3.payment_service.common.messages.PaymentCompletedEvent;
import com.bloque3.payment_service.config.RabbitConfig;
import com.bloque3.payment_service.models.Payment;
import com.bloque3.payment_service.repositories.PaymentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentEventListener {

    private final PaymentService paymentService;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitConfig.Q_PAYMENT_INVENTORY_EVENTS)
    public void onInventoryReservedEvent(Message message) {
        try {
            String json = new String(message.getBody());
            log.info("Received inventory reserved event: {}", json);
            InventoryReservedEvent event = objectMapper.readValue(json, InventoryReservedEvent.class);

            Payment payment = Payment.builder()
                    .orderId(event.orderId())
                    .amount(event.totalAmount())
                    .build();

            paymentService.save(payment).subscribe(savedPayment -> {
                log.info("Payment saved for order: {}", event.orderId());
                PaymentCompletedEvent completedEvent = new PaymentCompletedEvent(event.orderId());
                rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, "ev.payment-completed", completedEvent);
            }, error -> {
                log.error("Error saving payment", error);
                PaymentFailedEvent failedEvent = new PaymentFailedEvent(event.orderId(), event.productId(), event.quantity(), event.totalAmount(), error.getMessage());
                rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, "ev.payment-failed", failedEvent);
            });

        } catch (Exception e) {
            log.error("Error processing inventory reserved event", e);
        }
    }
}
