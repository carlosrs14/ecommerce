package com.bloque3.order_service.services;

import com.bloque3.order_service.dtos.request.OrderRequestDTO;
import com.bloque3.order_service.dtos.response.OrderResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {
    Mono<OrderResponseDTO> create(OrderRequestDTO request);
    Mono<OrderResponseDTO> findById(String id);
    Flux<OrderResponseDTO> findAll();
    Mono<Void> cancel(String id);
    Mono<Void> complete(String id);
    Mono<Void> reject(String id, String reason);
}
