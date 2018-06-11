package com.cd.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class DemoZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoZuulApplication.class, args);
	}
	
	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}
	
	@Bean
	public ValidatorFilter validatorFilter() {
		return new ValidatorFilter();
	}
}
