package com.bloque3.product_service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bloque3.product_service.dtos.request.ProductRequestDTO;
import com.bloque3.product_service.dtos.response.ProductResponseDTO;
import com.bloque3.product_service.models.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "hostname",ignore = true)
    ProductResponseDTO toDto(Product product);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    Product toEntity(ProductRequestDTO productRequestDTO);
}
