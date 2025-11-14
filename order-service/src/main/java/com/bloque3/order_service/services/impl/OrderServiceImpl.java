package com.bloque3.order_service.services.impl;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.bloque3.order_service.dtos.request.OrderRequestDTO;
import com.bloque3.order_service.dtos.response.OrderResponseDTO;
import com.bloque3.order_service.mappers.OrderMapper;
import com.bloque3.order_service.models.Order;
import com.bloque3.order_service.models.OrderStatus;
import com.bloque3.order_service.repositories.OrderRepository;
import com.bloque3.order_service.services.OrderService;

import lombok.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public Mono<OrderResponseDTO> create(OrderRequestDTO request) {
        Order order = orderMapper.toEntity(request);
        order.setCreatedAt(Instant.now());
        order.setStatus(OrderStatus.CREATED);
        return orderRepository.save(order).map(orderMapper::toDTO);
    }

    @Override
    public Mono<OrderResponseDTO> findById(@NonNull String id) {
        return orderRepository.findById(id).map(orderMapper::toDTO);
    }

    @Override
    public Flux<OrderResponseDTO> findAll() {
        return orderRepository.findAll().map(orderMapper::toDTO);
    }

    @Override
    public Mono<Void> cancel(@NonNull String id) {
        return orderRepository
            .findById(id)
            .flatMap(order -> {
                order.setStatus(OrderStatus.CANCELLED);
                return orderRepository.save(order);
            }).then();
    }

    @Override
    public Mono<Void> complete(@NonNull String id) {
        return orderRepository
            .findById(id)
            .flatMap(order -> {
                order.setStatus(OrderStatus.COMPLETED);
                return orderRepository.save(order);
            }).then();
    }

    @Override
    public Mono<Void> reject(@NonNull String id, String reason) {
        return orderRepository
            .findById(id)
            .flatMap(order -> {
                order.setStatus(OrderStatus.REJECTED);
                return orderRepository.save(order);
            }).then();
    }

}
