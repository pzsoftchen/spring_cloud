package com.fengchaoli.sidecar;

import com.fengchaoli.sidecar.config.SessionServiceSidecarProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableSidecar
@Configuration
@ComponentScan
@EnableConfigurationProperties
public class SidecarApplication {

	public static void main(String[] args) {
		SpringApplication.run(SidecarApplication.class, args);
	}

	@Autowired
	private SessionServiceSidecarProperties properties;

	@Bean
	public EurekaInstanceConfigBean eurekaInstanceConfigBean(InetUtils inetUtils) {
		final String sidecarHostname = properties.getHostname();
		final Integer sidecarPort = properties.getPort();

		final EurekaInstanceConfigBean instance = new EurekaInstanceConfigBean(inetUtils);
		instance.setHostname(sidecarHostname);
		instance.setIpAddress(sidecarHostname);
		instance.setNonSecurePort(sidecarPort);
		return instance;

	}
}
