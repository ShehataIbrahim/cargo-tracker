package com.streams.tracker.handling.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

@Profile("!test")
@Configuration
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        final List<Response> globalResponses = Arrays.asList(
                new ResponseBuilder().code("200").description("OK")
                        .build(),
                new ResponseBuilder().code("400").description("Bad Request")
                        .build(),
                new ResponseBuilder().code("500").description("Internal Error")
                        .build());
        return new Docket(DocumentationType.SWAGGER_2)
                .globalResponses(HttpMethod.GET, globalResponses)
                .globalResponses(HttpMethod.POST, globalResponses)
                .globalResponses(HttpMethod.DELETE, globalResponses)
                .globalResponses(HttpMethod.PATCH, globalResponses)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
