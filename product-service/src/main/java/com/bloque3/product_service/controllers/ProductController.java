package com.bloque3.product_service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloque3.product_service.dtos.request.ProductRequestDTO;
import com.bloque3.product_service.dtos.response.ProductResponseDTO;
import com.bloque3.product_service.services.ProductService;

import java.util.List;

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
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<ProductResponseDTO> save(@RequestBody @Validated ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(productService.save(productRequestDTO));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @RequestBody @Validated ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(productService.update(id, productRequestDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> patch(@PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(productService.patch(id, productRequestDTO));
    }

    @PostMapping("/{id}/archive")
    public ResponseEntity<ProductResponseDTO> archive(@PathVariable Long id) {
        return ResponseEntity.ok(productService.archive(id));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
