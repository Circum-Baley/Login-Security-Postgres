package com.userlogin.userApp;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import com.github.javafaker.Faker;
//import com.userlogin.userApp.config.FakerBeanConfig;
import com.userlogin.userApp.entities.Role;
import com.userlogin.userApp.entities.User;
import com.userlogin.userApp.entities.UserInRole;
import com.userlogin.userApp.repositories.RoleRepository;
import com.userlogin.userApp.repositories.UserInRoleRepository;
import com.userlogin.userApp.repositories.UserRepository;

@SpringBootApplication
@ComponentScan("com.userlogin.userApp") //to scan packages mentioned /Login-Security-CRS/src/main/java/com/userlogin/userApp/LoginSecurityCrsApplication.java
@EnableJpaRepositories("com.userlogin.userApp.repositories")
public class LoginSecurityCrsApplication implements ApplicationRunner{
	
	private final static Logger log = LoggerFactory.getLogger(LoginSecurityCrsApplication.class);
	
	@Autowired
	private Faker faker;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private UserInRoleRepository userInRoleRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(LoginSecurityCrsApplication.class, args);
		System.out.println("Benvenidos мать ублюдок");	
		}
	
	/*
	*   LandingPage
	*/
	public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/").setViewName("forward:/index");
	}
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Role roles[] = {new Role("ADMIN"), new Role("ROOT"), new Role("USER"), new Role("SUPPORT")};
		
		for (Role role : roles) {
			roleRepository.save(role);
		}
		
		for (int i = 0; i < 20; i++) {
			User user = new User();
			user.setUsername(faker.name().username());
			user.setPassword(faker.dragonBall().character());
			User created = userRepository.save(user);
			UserInRole userInRole = new UserInRole(created,roles[new Random().nextInt(4)]);
			log.info("Usuario Creado - Username {} - Password {} - rol {}",created.getUsername(),created.getPassword(),userInRole.getRole().getName());
			userInRoleRepository.save(userInRole);
		}
		
	}

}
