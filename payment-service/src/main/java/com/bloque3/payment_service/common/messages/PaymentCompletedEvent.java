package com.bloque3.payment_service.common.messages;

public record PaymentCompletedEvent(String orderId, Float amount) {
    
}
