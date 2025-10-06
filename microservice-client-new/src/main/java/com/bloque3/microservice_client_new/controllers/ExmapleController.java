package com.bloque3.microservice_client_new.controllers;

import com.bloque3.microservice_client_new.clients.ProductClient;
import com.bloque3.microservice_client_new.dtos.request.ProductRequestDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/new-version")
@CrossOrigin(origins = "http://localhost:4200")
public class ExmapleController {

    private final ProductClient productClient;

    public ExmapleController(ProductClient productClient) {
        this.productClient = productClient;
    }

    @GetMapping("")
    public String getHostName() {
        return productClient.getHostName();
    }

    @GetMapping("/products")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(productClient.findAll());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productClient.findById(id));
    }

    @PostMapping("/products")
    public ResponseEntity<?> create(@RequestBody ProductRequestDTO product) {
        return ResponseEntity.ok(productClient.create(product));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductRequestDTO product) {
        return ResponseEntity.ok(productClient.update(id, product));
    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<?> patch(@PathVariable Long id, @RequestBody ProductRequestDTO product) {
        return ResponseEntity.ok(productClient.patch(id, product));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productClient.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    
    

}
