package com.bloque3.product_service.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bloque3.product_service.dtos.request.ProductRequestDTO;
import com.bloque3.product_service.dtos.response.ProductResponseDTO;
import com.bloque3.product_service.mappers.ProductMapper;
import com.bloque3.product_service.models.Product;
import com.bloque3.product_service.repositories.ProductRespository;
import com.bloque3.product_service.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    
    private final ProductMapper productMapper;

    private final ProductRespository productRespository;

    public ProductServiceImpl(ProductMapper productMapper, ProductRespository productRespository) {
        this.productMapper = productMapper;
        this.productRespository = productRespository;
    }

    @Override
    public ProductResponseDTO findById(Long id) {
        Product product = productRespository.findById(id).orElseThrow();
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductResponseDTO> findAll() {
        List<Product> products = productRespository.findAll();
        return products.stream().map(productMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO save(ProductRequestDTO productRequestDTO) {
        Product product = productMapper.toEntity(productRequestDTO);
        product = productRespository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public ProductResponseDTO update(Long id, ProductRequestDTO productRequestDTO) {
        Product product = productRespository.findById(id).orElseThrow();
        product.setName(productRequestDTO.getName());
        product.setDescription(productRequestDTO.getDescription());
        product.setPrice(productRequestDTO.getPrice());
        product.setStock(productRequestDTO.getStock());
        product = productRespository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public ProductResponseDTO patch(Long id, ProductRequestDTO productRequestDTO) {
        Product product = productRespository.findById(id).orElseThrow();
        if (productRequestDTO.getName() != null) {
            product.setName(productRequestDTO.getName());
        }
        product = productRespository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public void delete(Long id) {
        if (productRespository.existsById(id)) {
            productRespository.deleteById(id);
        }
    }
}
