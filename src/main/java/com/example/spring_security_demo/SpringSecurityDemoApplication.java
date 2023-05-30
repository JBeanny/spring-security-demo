package com.example.spring_security_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityDemoApplication {

	public static void main(String[] args) {
		System.out.println("Server is running...");
		SpringApplication.run(SpringSecurityDemoApplication.class, args);
	}

}
