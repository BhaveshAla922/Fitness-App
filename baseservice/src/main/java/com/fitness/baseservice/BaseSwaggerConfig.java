package com.fitness.baseservice;

import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;

public class BaseSwaggerConfig {

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

}
