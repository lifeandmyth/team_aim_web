package com.project.aim.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication (exclude = SecurityAutoConfiguration.class)
@ComponentScan("com.project.aim")
public class ProjectaimApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectaimApplication.class, args);
	}

}
