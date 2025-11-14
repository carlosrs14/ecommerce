package com.bloque3.product_service.services.impl;

import org.springframework.stereotype.Service;

import com.bloque3.product_service.dtos.request.ProductRequestDTO;
import com.bloque3.product_service.dtos.response.ProductResponseDTO;
import com.bloque3.product_service.mappers.ProductMapper;
import com.bloque3.product_service.models.Product;
import com.bloque3.product_service.repositories.ProductRespository;
import com.bloque3.product_service.services.ProductService;

import lombok.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {
    
    private final ProductMapper productMapper;
    private final ProductRespository productRespository;

    public ProductServiceImpl(ProductMapper productMapper, ProductRespository productRespository) {
        this.productMapper = productMapper;
        this.productRespository = productRespository;
    }

    @Override
    public Mono<ProductResponseDTO> findById(@NonNull String id) {
        return productRespository.findById(id).map(productMapper::toDto);
    }

    @Override
    public Flux<ProductResponseDTO> findAll() {
        return productRespository.findAll().map(productMapper::toDto);
    }

    @Override
    public Mono<ProductResponseDTO> save(ProductRequestDTO productRequestDTO) {
        Product product = productMapper.toEntity(productRequestDTO);
        if (product == null) throw new RuntimeException();
        return productRespository.save(product).map(productMapper::toDto);
    }

    @Override
    public Mono<ProductResponseDTO> update(String id, ProductRequestDTO productRequestDTO) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
    }

    @Override
    public Mono<ProductResponseDTO> patch(String id, ProductRequestDTO productRequestDTO) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Mono<ProductResponseDTO> archive(String id) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Mono<Void> delete(@NonNull String id) {
        return productRespository.deleteById(id);
    }
}
