package com.example.demo.swaggerui;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.time.LocalDate;


@Configuration
public class SwaggerConfig {
    public static final String HABIT_CTRL = "Habit Controller";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo"))
                .build()
                .directModelSubstitute(LocalDate.class, java.sql.Date.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .tags(new Tag(HABIT_CTRL, HABIT_CTRL))
                .apiInfo(getApiInformation());
    }

    private ApiInfo getApiInformation() {
        return new ApiInfoBuilder().title("Habit Tracker API").version("1.0.0").build();
    }

    @Autowired
    TypeResolver typeResolver;
}
