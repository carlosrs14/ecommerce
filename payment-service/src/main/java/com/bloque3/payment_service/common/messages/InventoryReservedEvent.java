package com.bloque3.payment_service.common.messages;

public record InventoryReservedEvent(String orderId, String productId, Integer quantity, Float totalAmount) {
    
}
