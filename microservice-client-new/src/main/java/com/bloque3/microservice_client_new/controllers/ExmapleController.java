package com.bloque3.microservice_client_new.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloque3.microservice_client_new.clients.ExmapleClient;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/new-version")
public class ExmapleController {

    private final ExmapleClient exmapleClient;

    public ExmapleController(ExmapleClient exmapleClient) {
        this.exmapleClient = exmapleClient;
    }

    @GetMapping("")
    public String getHostName() {
        return exmapleClient.getHostName();
    }
}
