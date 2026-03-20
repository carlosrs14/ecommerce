package com.bloque3.order_service.common.messages;

public record OrderCancelledEvent(String orderId, String reason) {
    
}
