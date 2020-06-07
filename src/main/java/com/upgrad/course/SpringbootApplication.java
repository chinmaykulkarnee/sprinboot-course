package com.upgrad.course;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

// TODO: Mark this class as a Spring boot application using correct annotation
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

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}
		};
	}

}
