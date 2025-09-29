package com.bloque3.product_service.services;

import java.util.List;

import com.bloque3.product_service.dtos.request.ProductRequestDTO;
import com.bloque3.product_service.dtos.response.ProductResponseDTO;

public interface ProductService {
    ProductResponseDTO findById(Long id);
    List<ProductResponseDTO> findAll();
    ProductResponseDTO save(ProductRequestDTO productRequestDTO);
    ProductResponseDTO update(Long id, ProductRequestDTO productRequestDTO);
    ProductResponseDTO patch(Long id, ProductRequestDTO productRequestDTO);
    void delete(Long id);
}
