package com.bloque3.payment_service.common.messages;

public record PaymentFailedEvent(String orderId, String productId, Integer quantity, Float amount, String reason) {
    
}
