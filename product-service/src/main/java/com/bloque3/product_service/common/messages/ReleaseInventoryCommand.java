package com.bloque3.product_service.common.messages;

public record ReleaseInventoryCommand(String orderId, String productId, Integer quantity) {
    
}
