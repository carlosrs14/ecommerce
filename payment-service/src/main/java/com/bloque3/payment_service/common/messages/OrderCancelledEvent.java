package com.bloque3.payment_service.common.messages;

public record OrderCancelledEvent(String orderId, String reason) {
    
}
