package com.api.client.person;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ApiClientPersonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiClientPersonApplication.class, args);
	}

}
