package com.bloque3.order_service.dtos.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderRequestDTO {
    private String productId;
    private Integer quantity;
    private Float totalAmount;
}
