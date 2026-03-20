package com.bloque3.product_service.common.messages;

public record ProcessPaymentCommand(String orderId, Float amount) {
    
}
