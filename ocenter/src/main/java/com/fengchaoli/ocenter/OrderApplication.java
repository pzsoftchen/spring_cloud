package com.fengchaoli.ocenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


//@EnableDiscoveryClient
//@Configuration
@EnableSpringConfigured
@EnableLoadTimeWeaving
@SpringBootApplication
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}
}
