package com.bloque3.payment_service.common.messages;

public record ReleaseInventoryCommand(String orderId, String productId, Integer quantity) {
    
}
