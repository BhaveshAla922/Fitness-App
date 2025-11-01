package com.fitness.userservice.base;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenApiCustomizer sortPathsAlphabetically() {
        return openApi -> {
            if (openApi.getPaths() == null) {
                return;
            }

            Map<String, PathItem> sorted = new LinkedHashMap<>();
            openApi.getPaths().entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEachOrdered(e -> sorted.put(e.getKey(), e.getValue()));

            Paths newPaths = new Paths();
            sorted.forEach(newPaths::addPathItem);

            openApi.setPaths(newPaths);
        };
    }

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
