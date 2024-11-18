package com.utils_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UtilsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UtilsMicroserviceApplication.class, args);
	}

}
