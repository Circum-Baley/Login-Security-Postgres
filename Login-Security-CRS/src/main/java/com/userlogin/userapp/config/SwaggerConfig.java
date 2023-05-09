package com.userlogin.userapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
//				apiInfo()-> crea una api y obtiene info de la misma
				.apiInfo(getApiInfo())
//				Muestra por UI todas las api seleccionadas
//				.select().apis(RequestHandlerSelectors.any())
//				muestra todas las api seleccionadas pero de una base en particular
				.select().apis(RequestHandlerSelectors.basePackage("com.userlogin.userapp.controllers"))
//				.paths(PathSelectors.any()).build();
//				Solo seleccionara las api que esten creadas dentro de ant("/")
				.paths(PathSelectors.ant("/**"))
				.build();
	}
	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("API's CIRBALEY")
				.version("4.4")
				.license("License")
				.contact(new Contact("@Cirbaley", "www.cirbal.com", "Gonzalo.bahamondez@yandex.com"))
				.build();
	}
	
}

/*
 * para poder ingresar a la interfaz de swagger y poder visualizar los endpoints
 *localhost:8080/swagger-ui.html
 * 
 * archivo swagger obtiene los endpoints de manera dinamica 
 */