package com.bloque3.product_service.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.env.Environment;
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

    private final Environment env;

    public ProductServiceImpl(ProductMapper productMapper, ProductRespository productRespository, Environment env) {
        this.productMapper = productMapper;
        this.productRespository = productRespository;
        this.env = env;
    }

    @Override
    public ProductResponseDTO findById(Long id) {
        Product product = productRespository.findById(id).orElseThrow();
        ProductResponseDTO productResponseDTO = productMapper.toDto(product);
        productResponseDTO.setHostname(env.getProperty("HOSTNAME"));
        return productResponseDTO;
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
        ProductResponseDTO productResponseDTO = productMapper.toDto(product);
        productResponseDTO.setHostname(env.getProperty("HOSTNAME"));
        return productResponseDTO;
    }

    @Override
    public ProductResponseDTO update(Long id, ProductRequestDTO productRequestDTO) {
        Product product = productRespository.findById(id).orElseThrow();
        product.setName(productRequestDTO.getName());
        product.setDescription(productRequestDTO.getDescription());
        product.setPrice(productRequestDTO.getPrice());
        product.setStock(productRequestDTO.getStock());
        product = productRespository.save(product);

        ProductResponseDTO productResponseDTO = productMapper.toDto(product);
        productResponseDTO.setHostname(env.getProperty("HOSTNAME"));
        return productResponseDTO;
    }

    @Override
    public ProductResponseDTO patch(Long id, ProductRequestDTO productRequestDTO) {
        Product product = productRespository.findById(id).orElseThrow();
        if (productRequestDTO.getName() != null) {
            product.setName(productRequestDTO.getName());
        }
        product = productRespository.save(product);
        ProductResponseDTO productResponseDTO = productMapper.toDto(product);
        productResponseDTO.setHostname(env.getProperty("HOSTNAME"));
        return productResponseDTO;
    }

    @Override
    public ProductResponseDTO archive(Long id) {
        Product product = productRespository.findById(id).orElseThrow();
        product.setActive(!product.getActive());
        product = productRespository.save(product);
        ProductResponseDTO productResponseDTO = productMapper.toDto(product);
        return productResponseDTO;
    }

    @Override
    public void delete(Long id) {
        if (productRespository.existsById(id)) {
            productRespository.deleteById(id);
        }
    }
}
