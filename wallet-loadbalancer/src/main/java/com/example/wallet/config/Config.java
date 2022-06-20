package com.example.wallet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.example.wallet.handler.RestTemplateResponseErrorHandler;

@Configuration
public class Config {
	
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate=new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		return new RestTemplate();
	}

}
