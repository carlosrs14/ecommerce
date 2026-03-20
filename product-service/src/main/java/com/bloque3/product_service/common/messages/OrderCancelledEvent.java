package com.bloque3.product_service.common.messages;

public record OrderCancelledEvent(String orderId, String reason) {
    
}
