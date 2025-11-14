package com.bloque3.order_service.common.messages;

public record ReserveInventoryCommand(String orderId, String productId, Integer quantity) {
    
}
