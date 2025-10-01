package com.bloque3.microservice_client_new.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {
    private String hostname;
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
}
