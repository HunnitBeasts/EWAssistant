package com.hunnit_beasts.EWAssistant.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
        @Bean
        public OpenAPI openAPI() {
                return new OpenAPI()
                        .info(apiInfo());
        }
        private Info apiInfo() {
                return new Info()
                        .title("Kelog API Documentaion")
                        .description("Kelogs Swagger UI APIs")
                        .version("v1.0.0");
        }
}
