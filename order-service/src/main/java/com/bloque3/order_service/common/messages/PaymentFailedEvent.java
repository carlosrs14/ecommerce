package com.bloque3.order_service.common.messages;

public record PaymentFailedEvent(String orderId, Float amount, String reason) {
    
}
