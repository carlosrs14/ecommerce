package com.bloque3.order_service.common.messages;

public record PaymentCompletedEvent(String orderId, Float amount) {
    
}
