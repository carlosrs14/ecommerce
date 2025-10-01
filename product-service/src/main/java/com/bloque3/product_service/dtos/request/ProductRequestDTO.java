package com.bloque3.product_service.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDTO {
    @NotBlank(message = "Must provide a name")
    private String name;

    private String description;
    
    @Positive
    private Double price;
    
    @PositiveOrZero
    private Integer stock;
}
