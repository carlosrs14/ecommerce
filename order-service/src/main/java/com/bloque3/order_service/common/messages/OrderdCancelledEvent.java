package com.bloque3.order_service.common.messages;

public record OrderdCancelledEvent(String orderId, String reason) {
    
}
