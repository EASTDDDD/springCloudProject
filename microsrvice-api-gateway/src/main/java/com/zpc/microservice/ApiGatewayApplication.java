package com.zpc.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

@SpringBootApplication
@EnableZuulProxy
public class ApiGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	
	@RefreshScope
	@ConfigurationProperties("zuul")	
	public ZuulProperties zuulProperties() {
		ZuulProperties zuulProperties = new ZuulProperties();
		System.out.println("zuulProperties:" + zuulProperties);
		return zuulProperties;
	}
}
