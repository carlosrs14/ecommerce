package com.bloque3.payment_service.services.impl;

import org.springframework.stereotype.Service;

import com.bloque3.payment_service.models.Payment;
import com.bloque3.payment_service.repositories.PaymentRepository;
import com.bloque3.payment_service.services.PaymentService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Mono<Payment> findById(String id) {
        return paymentRepository.findById(id);
    }

    @Override
    public Flux<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Flux<Payment> findByOrderId(String orderId) {
        // We'll need to add this method to the repository if we want to filter by orderId
        return paymentRepository.findAll().filter(p -> p.getOrderId().equals(orderId));
    }

    @Override
    public Mono<Payment> save(Payment payment) {
        return paymentRepository.save(payment);
    }
}
