package com.bloque3.product_service.common.messages;

public record PaymentFailedEvent(String orderId, String productId, Integer quantity, Float amount, String reason) {
    
}
