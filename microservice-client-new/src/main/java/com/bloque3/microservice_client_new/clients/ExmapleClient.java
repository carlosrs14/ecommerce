package com.bloque3.microservice_client_new.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "eureka-client", path = "/api/v1")
public interface ExmapleClient {

    @GetMapping("/hostname")
    String getHostName();
}
