package com.aerotaller.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AeroTaller API - Misión Crítica")
                        .version("1.0.0-MVP")
                        .description("Documentación de API para WMS y MRO Aeronáutico. \n\n" +
                                "**Regla de Oro:** Todo endpoint debe manejar DTOs, nunca Entidades directas.")
                        .contact(new Contact()
                                .name("Equipo de Arquitectura")
                                .email("lider.tecnico@aerotaller.com")));
    }
}