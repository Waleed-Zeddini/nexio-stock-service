package com.nexio.api.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient         // To enable eureka client
public class StockService {

	public static void main(String[] args) {
		SpringApplication.run(StockService.class, args);
	}

}
