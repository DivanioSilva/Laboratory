package com.example.mydemo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

        @Bean
        public OpenAPI openApi() {
            return new OpenAPI()
                    .info(new Info()
                            .title("This project has several interesting techonologies and is used as a learning laboratory.")
                            .description("Laboratory API")
                            .version("v1.0")
                            .contact(new Contact()
                                    .name("Divanio Silva")
                                    .url("https://asbnotebook.com")
                                    .email("dcsilva@mail.ru"))
                            .termsOfService("TOC")
                            .license(new License().name("License").url("#"))
                    );
        }
}
