package com.upgrad.course;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {
		System.out.println("Welcome to Spring Boot");
		SpringApplication.run(SpringbootApplication.class, args);
	}

	// You can this result printed when you will run the application
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Let's inspect the beans initialized by Spring Boot:");

			List<String> beanNames = Arrays.asList(ctx.getBeanDefinitionNames());
			System.out.println("Repository bean created : " + beanNames.contains("orderRepository"));
			System.out.println("Service bean created : " + beanNames.contains("orderService"));
			System.out.println("Controller bean created : " + beanNames.contains("orderController"));
		};
	}
}
