package com.bloque3.order_service.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloque3.order_service.dtos.request.OrderRequestDTO;
import com.bloque3.order_service.dtos.response.OrderResponseDTO;
import com.bloque3.order_service.services.OrderService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<OrderResponseDTO>> findById(@PathVariable String id) {
        return orderService.findById(id).map(ResponseEntity::ok);
    }

    @GetMapping("")
    public Flux<OrderResponseDTO> findAll() {
        return orderService.findAll();
    }
    
    @PostMapping("")
    public Mono<ResponseEntity<OrderResponseDTO>> create(@RequestBody OrderRequestDTO request) {
        return orderService.create(request).map(ResponseEntity::ok);
    }
}
