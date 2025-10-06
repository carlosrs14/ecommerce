package com.bloque3.product_service.controllers;

import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class HostNameController {
    private Environment env;

    public HostNameController(Environment env) {
        this.env = env;
    }

    @GetMapping("/hostname")
    public ResponseEntity<String> get() {
        String hostname = env.getProperty("HOSTNAME");
        return ResponseEntity.ok().body("hola mundo from: " + hostname);
    }
    
}
