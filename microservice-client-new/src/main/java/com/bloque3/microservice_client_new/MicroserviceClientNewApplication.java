package com.bloque3.microservice_client_new;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceClientNewApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceClientNewApplication.class, args);
	}

}
