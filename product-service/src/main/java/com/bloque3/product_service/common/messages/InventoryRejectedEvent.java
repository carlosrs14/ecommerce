package com.bloque3.product_service.common.messages;

public record InventoryRejectedEvent(String orderId, String productId, Integer quantity, String reason) {
    
}
