package com.naik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class JrtpConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JrtpConfigServerApplication.class, args);
	}

}
