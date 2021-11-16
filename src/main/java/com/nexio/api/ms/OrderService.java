package com.nexio.api.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
//@EnableEurekaClient         // To enable Eureka client (Register of the MS)
@EnableFeignClients // To enable Open Feign client
public class OrderService {

	public static void main(String[] args) {
		SpringApplication.run(OrderService.class, args);
	}

}
