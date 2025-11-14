package com.bloque3.order_service.common.messages;

public record ProcessPaymentCommand(String orderId, Float amount) {
    
}
