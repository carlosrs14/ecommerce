package com.bloque3.product_service.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.bloque3.product_service.models.Product;

@Repository
public interface ProductRespository extends ReactiveCrudRepository<Product, String>{
    
}
