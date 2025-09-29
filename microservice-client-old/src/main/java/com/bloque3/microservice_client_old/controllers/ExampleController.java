package com.bloque3.microservice_client_old.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/old-version")
public class ExampleController {
    private final RestTemplate restTemplate;

    public ExampleController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("")
    public String hostName() {
        String serviceUrl = "http://product-service:8000/api/v1/hostname";
        return restTemplate.getForObject(serviceUrl, String.class);
    }
}
