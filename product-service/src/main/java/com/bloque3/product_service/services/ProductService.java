package com.bloque3.product_service.services;

import com.bloque3.product_service.dtos.request.ProductRequestDTO;
import com.bloque3.product_service.dtos.response.ProductResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<ProductResponseDTO> findById(String id);
    Flux<ProductResponseDTO> findAll();
    Mono<ProductResponseDTO> save(ProductRequestDTO productRequestDTO);
    Mono<ProductResponseDTO> update(String id, ProductRequestDTO productRequestDTO);
    Mono<ProductResponseDTO> patch(String id, ProductRequestDTO productRequestDTO);
    Mono<ProductResponseDTO> archive(String id);
    Mono<Void> delete(String id);
}
