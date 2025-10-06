package com.bloque3.microservice_client_old.controllers;

import com.bloque3.microservice_client_old.dtos.request.ProductRequestDTO;
import com.bloque3.microservice_client_old.dtos.response.ProductResponseDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/old-version")
@CrossOrigin(origins = "http://localhost:4200")
public class ExampleController {
    private final RestTemplate restTemplate;
    final String serviceUrl = "http://product-service:8000/api/v1";

    public ExampleController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("")
    public ResponseEntity<String> hostName() {
        String urlFinal = serviceUrl + "/hostname";
        return restTemplate.getForEntity(urlFinal, String.class);
    }

    @GetMapping("/products")
    public ResponseEntity<ProductResponseDTO[]> findAll() {
        String urlFinal = serviceUrl + "/products";
        return restTemplate.getForEntity(urlFinal, ProductResponseDTO[].class);  
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDTO> findbyId(@PathVariable Long id) {
        String urlFinal = serviceUrl + "/products/" + id;
        return restTemplate.getForEntity(urlFinal, ProductResponseDTO.class); 
        
    }
    
    @PostMapping("/products")
    public ResponseEntity<ProductResponseDTO> create(@RequestBody ProductRequestDTO product) {
        String urlFinal = serviceUrl + "/products";
        return restTemplate.postForEntity(urlFinal, product, ProductResponseDTO.class);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductRequestDTO product) {
        String urlFinal = serviceUrl + "/products/" + id;
        restTemplate.put(urlFinal, product);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<ProductResponseDTO> patch(@PathVariable Long id, @RequestBody ProductRequestDTO product) {
        String urlFinal = serviceUrl + "/products/" + id;
        ProductResponseDTO resp = restTemplate.patchForObject(urlFinal, product, ProductResponseDTO.class);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/products/{id}/archive")
    public ResponseEntity<?> archive(@PathVariable Long id) {
        String urlFinal = serviceUrl + "/products/" + id + "/archive";
        return restTemplate.postForEntity(urlFinal, null, ProductResponseDTO.class);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        String urlFinal = serviceUrl + "/products/" + id;
        restTemplate.delete(urlFinal);
        return ResponseEntity.noContent().build();
    }
}
