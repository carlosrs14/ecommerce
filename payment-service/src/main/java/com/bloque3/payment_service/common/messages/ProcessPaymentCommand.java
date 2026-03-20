package com.bloque3.payment_service.common.messages;

public record ProcessPaymentCommand(String orderId, Float amount) {
    
}
