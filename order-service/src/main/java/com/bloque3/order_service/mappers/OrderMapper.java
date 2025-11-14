package com.bloque3.order_service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bloque3.order_service.dtos.request.OrderRequestDTO;
import com.bloque3.order_service.dtos.response.OrderResponseDTO;
import com.bloque3.order_service.models.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Order toEntity(OrderRequestDTO dto);
    
    OrderResponseDTO toDTO(Order entity);
}
