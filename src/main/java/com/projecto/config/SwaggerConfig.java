package com.projecto.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.projecto.controllers"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo());
	}
	
	
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Documentación del proyecto",
				"Este proyecto es un api para manipular empleados",
				"1.0",
				"https://www.google.com",
				new Contact("Daniel", "pagina web", "arellanosalvador@gmail.com"),
				"",
				"",
				Collections.emptyList());
	}

}
