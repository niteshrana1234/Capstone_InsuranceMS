package com.takeo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket apiDocket() {
        Docket docket =  new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.takeo.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
        return docket;

    }
    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Insurance Management System (User Microservice)")
                .description("REST API endpoints for managing users in the Insurance Management System.")
                .version("1.0.0")
                .contact(new Contact("NRM group", "https://www.nrm.com/", "niteshrana1234@gmail.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }
}
