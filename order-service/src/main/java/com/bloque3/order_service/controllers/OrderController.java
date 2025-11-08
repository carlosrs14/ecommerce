package com.bloque3.order_service.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    
    @GetMapping("")
    public String test() {
        return "test from orders service";
    }
}
