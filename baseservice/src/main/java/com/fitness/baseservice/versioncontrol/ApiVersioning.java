package com.fitness.baseservice.versioncontrol;


import java.lang.reflect.Method;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.lang.NonNull;

@Configuration
public class ApiVersioning {
    
    private static final String DEFAULT_API_PREFIX = "/api/v1";

    @Bean
    public WebMvcRegistrations webMvcRegistrations() {
        return new WebMvcRegistrations() {

            @Override
            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
                return new RequestMappingHandlerMapping() {

                    @SuppressWarnings("null")
                    @Override
                    protected void registerHandlerMethod(
                        @NonNull Object handler,
                        @NonNull Method method,
                        @NonNull RequestMappingInfo mapping
                    ) {

                        ApiVersion classVersion = AnnotatedElementUtils.findMergedAnnotation(method.getDeclaringClass(), ApiVersion.class);

                        final String prefix =
                            (classVersion != null && classVersion.prefix() != null && !classVersion.prefix().isEmpty()) ? classVersion.prefix() :
                            DEFAULT_API_PREFIX;

                        RequestMappingInfo newMapping =
                            RequestMappingInfo.paths(prefix)
                                .build()
                                .combine(mapping);

                        super.registerHandlerMethod(handler, method, newMapping);

                    }

                };
            }
        };

    }
}