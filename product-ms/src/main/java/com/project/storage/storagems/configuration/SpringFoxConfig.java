package com.project.storage.storagems.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.project.storage.storagems.controller.impl"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(true)
                .globalResponses(HttpMethod.GET, Arrays.asList(
                        new ResponseBuilder().code("200").description("Ok").build(),
                        new ResponseBuilder().code("204").description("No Content").build(),
                        new ResponseBuilder().code("404").description("Not Found").build(),
                        new ResponseBuilder().code("400").description("Bad Request").build()
                )).globalResponses(HttpMethod.POST, Arrays.asList(
                        new ResponseBuilder().code("201").description("Created").build(),
                        new ResponseBuilder().code("400").description("Bad Request").build(),
                        new ResponseBuilder().code("404").description("Not Found").build()
                )).globalResponses(HttpMethod.PUT, Arrays.asList(
                        new ResponseBuilder().code("204").description("No Content").build(),
                        new ResponseBuilder().code("400").description("Bad Request").build(),
                        new ResponseBuilder().code("404").description("Not Found").build()
                )).globalResponses(HttpMethod.DELETE, Arrays.asList(
                        new ResponseBuilder().code("204").description("No Content").build(),
                        new ResponseBuilder().code("400").description("Bad Request").build(),
                        new ResponseBuilder().code("404").description("Not Found").build()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Products API",
                "API REST de productos.",
                "V1",
                "https://www.gnu.org/licenses/gpl-3.0.txt",
                new Contact("Developer", "https://www.linkedin.com/in/julio-%C3%A1ngel-hern%C3%A1ndez-montero-1b83019a/",
                        "julioangel.hernandez@gmail.com"),
                "GNU General Public License v3.0", "https://www.gnu.org/licenses/gpl-3.0.txt", Collections.emptyList());
    }
}