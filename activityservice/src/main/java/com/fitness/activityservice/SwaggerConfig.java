package com.fitness.activityservice;

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
            .title("Fitness Activity Service API")
            .description("""
                This API manages activity-related operations for the Fitness application.
                It handles activity tracking, workout logging, and related activity data.
                """)
            .version("1.0.0")
        );
    }
    
}
