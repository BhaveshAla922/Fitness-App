package com.fitness.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.fitness.userservice", "com.fitness.baseservice"})
public class UserserviceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}

}
