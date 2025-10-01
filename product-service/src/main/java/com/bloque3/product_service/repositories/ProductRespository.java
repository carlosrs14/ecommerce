package com.bloque3.product_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bloque3.product_service.models.Product;

@Repository
public interface ProductRespository extends JpaRepository<Product, Long>{
    
}
