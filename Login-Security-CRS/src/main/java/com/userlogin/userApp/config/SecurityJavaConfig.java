package com.userlogin.userApp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;




//   DEFINIMOS RECURSOS A USUARIOSZ Y ROLES

// los metodos se pueden evadir para poder continuar, ya que solo es relacionado con el usuario


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)// , jsr250Enabled = true)

public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

//	Definimos Usuarios y toles para ingresar por
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
		.withUser("admin").password(encoder().encode("password")).roles("ADMIN").and()
		.withUser("user").password(encoder().encode("password")).roles("USER").and()
		.withUser("root").password(encoder().encode("password")).roles("ROOT").and()
		.withUser("support").password(encoder().encode("password")).roles("SUPPORT");
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests()
//		.antMatchers("/api/user/**").hasRole("USER")
//		.antMatchers("/api/rol/**").permitAll()
		.anyRequest().authenticated()
		.and().httpBasic();	
//		http.csrf().disable().authorizeRequests()
////		.antMatchers("/api/users/**").hasRole("ADMIN")
//		.antMatchers("/api/**").permitAll() //.anyRequest().authenticated()
//		.and().httpBasic();
	}
	

	
	
	
	
	
}
