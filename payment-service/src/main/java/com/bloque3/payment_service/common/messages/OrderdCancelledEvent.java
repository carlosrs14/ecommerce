package com.bloque3.payment_service.common.messages;

public record OrderdCancelledEvent(String orderId, String reason) {
    
}
