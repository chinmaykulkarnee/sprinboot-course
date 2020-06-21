package com.upgrad.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {
		System.out.println("Welcome to Spring Boot");
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
