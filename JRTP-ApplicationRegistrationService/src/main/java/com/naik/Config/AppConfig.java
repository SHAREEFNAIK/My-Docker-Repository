package com.naik.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

	@Bean(name="webclient")
	public  WebClient createWebClient() {
		return  WebClient.create();
	}
	
}
