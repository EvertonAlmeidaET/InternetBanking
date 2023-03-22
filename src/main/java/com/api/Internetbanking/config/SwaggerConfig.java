package com.api.Internetbanking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpeanApi() {
    	return new OpenAPI()
    			.components(new Components())
    			.info(new Info().title("Internet Banking Aplicacao API")
    			.description("Este é um documento de rest API"));
    }
	
	
}
