package com.upgrad.course;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

// TODO: Mark this class as a Spring boot application using correct annotation
// This will automatically scan Entity, Repository and Service components and creates beans for them
public class SpringbootApplication {

	public static void main(String[] args) {
		System.out.println("Welcome to Spring Boot");
		SpringApplication.run(SpringbootApplication.class, args);
	}

	// You can see this result printed when you will run the application
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Let's inspect the beans initialized by Spring Boot:");

			List<String> beanNames = Arrays.asList(ctx.getBeanDefinitionNames());
			System.out.println("Repository bean created : " + beanNames.contains("userRepository"));
			System.out.println("Service bean created : " + beanNames.contains("userService"));
		};
	}
}
