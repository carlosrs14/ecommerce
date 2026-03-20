package com.bloque3.order_service.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.bloque3.order_service.models.Order;

@Repository
public interface OrderRepository extends ReactiveCrudRepository<Order, String> {
    
}
