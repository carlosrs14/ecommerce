package com.bloque3.order_service.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponseDTO {
    private String id;
    private String productId;
    private Integer quantity;
    private Float totalAmount;
    private String status;
    private String createdAt;
}
