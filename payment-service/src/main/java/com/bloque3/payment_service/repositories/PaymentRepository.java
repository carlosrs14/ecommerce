package com.bloque3.payment_service.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.bloque3.payment_service.models.Payment;

@Repository
public interface PaymentRepository extends ReactiveCrudRepository<Payment, String> {
}
