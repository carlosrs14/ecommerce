package com.bloque3.product_service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloque3.product_service.dtos.request.ProductRequestDTO;
import com.bloque3.product_service.dtos.response.ProductResponseDTO;
import com.bloque3.product_service.services.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public Flux<ProductResponseDTO> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ProductResponseDTO>> findById(@PathVariable String id) {
        return productService.findById(id).map(ResponseEntity::ok);
    }

    @PostMapping("")
    public Mono<ResponseEntity<ProductResponseDTO>> save(@RequestBody @Validated ProductRequestDTO productRequestDTO) {
        return productService.save(productRequestDTO).map(ResponseEntity::ok);
    }
    
    @PutMapping("/{id}")
    public Mono<ResponseEntity<ProductResponseDTO>> update(@PathVariable String id, @RequestBody @Validated ProductRequestDTO productRequestDTO) {
        return productService.update(id, productRequestDTO).map(ResponseEntity::ok);
    }

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<ProductResponseDTO>> patch(@PathVariable String id, @RequestBody ProductRequestDTO productRequestDTO) {
        return productService.patch(id, productRequestDTO).map(ResponseEntity::ok);
    }

    @PostMapping("/{id}/archive")
    public Mono<ResponseEntity<ProductResponseDTO>> archive(@PathVariable String id) {
        return productService.archive(id).map(ResponseEntity::ok);
    }
    
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return productService.delete(id).map(ResponseEntity::ok);
    }
}
