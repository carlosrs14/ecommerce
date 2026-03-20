package com.bloque3.payment_service.common.messages;

public record ReserveInventoryCommand(String orderId, String productId, Integer quantity) {
    
}
