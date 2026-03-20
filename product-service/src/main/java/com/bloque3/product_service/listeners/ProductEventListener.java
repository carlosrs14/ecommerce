package com.bloque3.product_service.listeners;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.bloque3.product_service.common.messages.InventoryRejectedEvent;
import com.bloque3.product_service.common.messages.InventoryReservedEvent;
import com.bloque3.product_service.common.messages.ReserveInventoryCommand;
import com.bloque3.product_service.config.RabbitConfig;
import com.bloque3.product_service.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductEventListener {

    private final ProductService productService;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitConfig.Q_INVENTORY_COMMANDS)
    public void onReserveInventoryCommand(Message message) {
        try {
            String json = new String(message.getBody());
            log.info("Received reserve inventory command: {}", json);
            ReserveInventoryCommand command = objectMapper.readValue(json, ReserveInventoryCommand.class);
            
            productService.reserveStock(command.productId(), command.quantity())
                .subscribe(product -> {
                    Float totalAmount = (float) (product.getPrice() * command.quantity());
                    InventoryReservedEvent event = new InventoryReservedEvent(
                            command.orderId(),
                            command.productId(),
                            command.quantity(),
                            totalAmount
                    );
                    rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, "ev.inventory-reserved", event);
                }, error -> {
                    log.error("Error reserving stock", error);
                }, () -> {
                    // Empty means product not found or not enough stock
                    InventoryRejectedEvent event = new InventoryRejectedEvent(command.orderId(), "Out of stock or invalid product");
                    rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, "ev.inventory-rejected", event);
                });
        } catch (Exception e) {
            log.error("Error processing reserve inventory command", e);
        }
    }

    @RabbitListener(queues = "product.payment-events")
    public void onPaymentFailedEvent(Message message) {
        try {
            String json = new String(message.getBody());
            log.info("Received payment failed event, compensating inventory: {}", json);
            com.bloque3.product_service.common.messages.PaymentFailedEvent event = objectMapper.readValue(json, com.bloque3.product_service.common.messages.PaymentFailedEvent.class);
            
            productService.releaseStock(event.productId(), event.quantity())
                .subscribe(product -> {
                    log.info("Successfully released stock for product: {} after payment failed for order: {}", event.productId(), event.orderId());
                }, error -> {
                    log.error("Error releasing stock for product: {}", event.productId(), error);
                });
        } catch (Exception e) {
            log.error("Error processing payment failed event", e);
        }
    }
}
