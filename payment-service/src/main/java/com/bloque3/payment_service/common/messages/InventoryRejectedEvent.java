package com.bloque3.payment_service.common.messages;

public record InventoryRejectedEvent(String orderId, String productId, Integer quantity, String reason) {
    
}
