package com.bloque3.payment_service.services;

import com.bloque3.payment_service.models.Payment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentService {
    Mono<Payment> findById(String id);
    Flux<Payment> findAll();
    Flux<Payment> findByOrderId(String orderId);
    Mono<Payment> save(Payment payment);
}
