package com.bloque3.microservice_client_old;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceClientOldApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceClientOldApplication.class, args);
	}

}
