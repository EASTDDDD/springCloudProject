package com.zpc.springcloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer  //声明这是一个Eureka服务
public class EurekaApp {

	public static void main(String[] args) {
		SpringApplication.run(EurekaApp.class, args);
	}
}
