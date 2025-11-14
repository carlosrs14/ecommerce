package com.bloque3.payment_service.common.messages;

public record PaymentFailedEvent(String orderId, Float amount, String reason) {
    
}
