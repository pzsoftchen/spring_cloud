package com.fengchaoli.ocenter;

import com.fengchaoli.basic.oauth2.feign.EnableOAuth2ClientFeign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


@EnableDiscoveryClient
@Configuration
@EnableSpringConfigured
@EnableLoadTimeWeaving
@SpringBootApplication
@EnableResourceServer
@EnableOAuth2Client
@EnableOAuth2ClientFeign
public class OcenterApplication extends ResourceServerConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(OcenterApplication.class, args);
	}
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/v2/api-docs").permitAll()
				.and().authorizeRequests().anyRequest().authenticated();
	}

	@Bean
	public InstrumentationLoadTimeWeaver loadTimeWeaver()  throws Throwable {
		InstrumentationLoadTimeWeaver loadTimeWeaver = new InstrumentationLoadTimeWeaver();
		return loadTimeWeaver;
	}
}
