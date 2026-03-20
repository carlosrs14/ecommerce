package com.bloque3.payment_service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloque3.payment_service.models.Payment;
import com.bloque3.payment_service.services.PaymentService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("")
    public Flux<Payment> findAll() {
        return paymentService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Payment>> findById(@PathVariable String id) {
        return paymentService.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/order/{orderId}")
    public Flux<Payment> findByOrderId(@PathVariable String orderId) {
        return paymentService.findByOrderId(orderId);
    }
}
