package com.userlogin.userapp.config;

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
@EnableGlobalMethodSecurity(jsr250Enabled = true)
//@EnableGlobalMethodSecurity(securedEnabled = true)
//@EnableGlobalMethodSecurity(prePostEnabled = true) // , jsr250Enabled = true)

public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

//	Definimos Usuarios y toles para ingresar por
//Solo se utiliza para agilizar el acceso a la base de datos para realizar pruebas y/o alguna programacion fugaz
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password(encoder().encode("password")).roles("ADMIN").and()
				.withUser("user").password(encoder().encode("password")).roles("USER").and().withUser("root")
				.password(encoder().encode("password")).roles("ROOT").and().withUser("support")
				.password(encoder().encode("password")).roles("SUPPORT");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.headers().frameOptions().disable();
		// Configuración para desactivar la protección CSRF y definir las reglas de
		// autorización
		// Se Excluiran las rutas de los endpoints
		http.csrf().disable().authorizeRequests()
				// Requiere que los usuarios tengan el rol "USER" para acceder a
				// "/api-consumption"
				.antMatchers("/hioools/**").hasRole("USER")
				// Requiere que los usuarios tengan el rol "USER" o "ROOT" para acceder a
				// "/api-vehicle/**"
				// .antMatchers("/api-user/**").hasAnyRole("ADMIN", "ROOT")
				.antMatchers("/hioools/**").permitAll().anyRequest().authenticated().and().httpBasic();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
