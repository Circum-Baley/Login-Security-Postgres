package com.userlogin.userapp.config;

//import org.springframework.security.web.util.matcher.MvcRequestMatcher;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// los metodos se pueden evadir para poder continuar, ya que solo es relacionado con el usuario

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig {

//	@Autowired
//	private LoginUserDetailsService login;

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

//	Definimos Usuarios y toles para ingresar por
//Solo se utiliza para agilizar el acceso a la base de datos para realizar pruebas y/o alguna programacion fugaz
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password(encoder().encode("password")).roles("ADMIN").and()
				.withUser("user").password(encoder().encode("password")).roles("USER").and().withUser("root")
				.password(encoder().encode("password")).roles("ROOT").and().withUser("support")
				.password(encoder().encode("password")).roles("SUPPORT");
	}
//
//	@Bean
//	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests((authorize) -> 
//		authorize.anyRequest().permitAll().requestMatchers("/api-role/**"));
//		return http.build();
//	}


    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
    SecurityFilterChain h2ConsoleSecurityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher(PathRequest.toH2Console());
        http.securityMatcher("/api-ro/**");
        http.csrf((csrf) -> csrf.disable());
        http.headers((headers) -> headers.frameOptions((frame) -> frame.sameOrigin()));
        return http.build();
    }
//
}
