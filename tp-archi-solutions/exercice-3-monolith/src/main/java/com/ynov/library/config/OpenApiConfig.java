package com.ynov.library.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI libraryOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Library Management API")
                        .description("RESTful API for managing books, users, and loans in a library system")
                        .version("1.0")
                        .contact(new Contact()
                                .name("YNOV DevSecOps")
                                .email("contact@ynov.com")));
    }
}
