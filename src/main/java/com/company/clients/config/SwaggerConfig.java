package com.company.clients.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Candidate Service - Gestión de Clientes")
                        .version("v1")
                        .description("API para registro, consulta y métricas de clientes"))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentación Swagger")
                        .url("https://swagger.io"));
    }
}

