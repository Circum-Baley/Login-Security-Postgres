package com.userlogin.userapp;

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
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import com.github.javafaker.Faker;
import com.userlogin.userapp.entities.Role;
import com.userlogin.userapp.entities.User;
import com.userlogin.userapp.entities.UserInRole;
import com.userlogin.userapp.repositories.RoleRepository;
import com.userlogin.userapp.repositories.UserInRoleRepository;
import com.userlogin.userapp.repositories.UserRepository;

@SpringBootApplication
@ComponentScan("com.userlogin.userapp") //to scan packages mentioned /Login-Security-CRS/src/main/java/com/userlogin/userApp/LoginSecurityCrsApplication.java
@EnableJpaRepositories("com.userlogin.userapp.repositories")
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
		log.info("Benvenidos мать ублюдок");	
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
			user.setPassword(faker.animal().name());
			
			User created = userRepository.save(user);
			
			UserInRole userInRole = new UserInRole(created,roles[new Random().nextInt(4)]);
			log.info("ID {} Usuario {} Password {} - ROL {}",created.getId(),created.getUsername(),created.getPassword(),userInRole.getRole().getName());
			userInRoleRepository.save(userInRole);
		}
		
	}

}
