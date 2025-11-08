package com.bloque3.order_service.common.messages;

public record ReleaseInventoryCommand(String orderId, String productId, Integer quantity) {
    
}
