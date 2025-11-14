package com.bloque3.product_service.common.messages;

public record PaymentFailedEvent(String orderId, Float amount, String reason) {
    
}
