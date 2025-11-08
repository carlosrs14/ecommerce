package com.bloque3.product_service.common.messages;

public record PaymentCompletedEvent(String orderId, Float amount) {
    
}
