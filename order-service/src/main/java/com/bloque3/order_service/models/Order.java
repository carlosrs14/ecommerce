package com.bloque3.order_service.models;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    private String id;

    @Column("product_id")
    private String productId;
    
    private Integer quantity;

    @Column("total_amount")
    private Float totalAmount;

    private OrderStatus status;
    
    @Column("created_at")
    private Instant createdAt;
}
