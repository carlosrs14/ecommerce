package com.bloque3.product_service.common.messages;

public record ReserveInventoryCommand(String orderId, String productId, Integer quantity) {
    
}
