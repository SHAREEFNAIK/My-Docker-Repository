package com.naik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class JrtpApplicationRegistrationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JrtpApplicationRegistrationServiceApplication.class, args);
	}

}
