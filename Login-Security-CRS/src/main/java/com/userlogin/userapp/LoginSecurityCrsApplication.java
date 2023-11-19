package com.userlogin.userapp;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan("com.userlogin.userapp") // to scan packages mentioned
@Configuration // /Login-Security-CRS/src/main/java/com/userlogin/userApp/LoginSecurityCrsApplication.java
@EnableJpaRepositories("com.userlogin.userapp.repositories")

public class LoginSecurityCrsApplication implements WebMvcConfigurer {// implements ApplicationRunner {

	private final static Logger log = LoggerFactory.getLogger(LoginSecurityCrsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LoginSecurityCrsApplication.class, args);
		log.info("Benvenidos мать ублюдок");
		UUID uuid = UUID.randomUUID();
		log.info("{}", uuid.toString());
	}

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/index.html");
	}

	

}
