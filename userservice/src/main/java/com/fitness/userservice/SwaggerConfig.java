package com.fitness.userservice;

import com.fitness.baseservice.BaseSwaggerConfig;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig extends BaseSwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
            .title("Fitness User Service API")
            .description("""
                This API manages user-related operations for the Fitness application.
                It handles registration, authentication, profile updates, and related user data.
                """)
            .version("1.0.0")
        );
    }

}
