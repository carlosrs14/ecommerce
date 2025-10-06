package com.bloque3.microservice_client_new.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bloque3.microservice_client_new.dtos.request.ProductRequestDTO;
import com.bloque3.microservice_client_new.dtos.response.ProductResponseDTO;

@FeignClient(name = "product-service", path = "/api/v1")
public interface ProductClient {

    @GetMapping("/hostname")
    String getHostName();

    @GetMapping("/products")
    List<ProductResponseDTO> findAll();

    @GetMapping("/products/{id}")
    ProductResponseDTO findById(@PathVariable Long id);

    @PostMapping("/products")
    ProductResponseDTO create(@RequestBody ProductRequestDTO product);

    @PutMapping("/products/{id}")
    ProductResponseDTO update(@PathVariable Long id, @RequestBody ProductRequestDTO product);

    @PatchMapping("/products/{id}")
    ProductResponseDTO patch(@PathVariable Long id, @RequestBody ProductRequestDTO product);

    @PostMapping("/products/{id}/archive")
    ProductResponseDTO archive(@PathVariable Long id);

    @DeleteMapping("/products/{id}")
    void delete(@PathVariable Long id);
}
