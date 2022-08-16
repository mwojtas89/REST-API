package com.crud.tasks.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;



@Configuration
public class CoreConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("task")
                .pathsToMatch("/**")
                .packagesToScan("com.crud.tasks.controller")
                .build();
    }


    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI();
    }
}
