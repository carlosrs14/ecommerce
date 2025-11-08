package com.bloque3.payment_service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    @GetMapping("")
    public String test() {
        return "test from payment service";
    }
    
}
