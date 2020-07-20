package fr.esgi.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket swaggerConfigApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.esgi.microservices"))
                .build();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Contact Application API",
                "Application Micro-Services with Spring Boot RESTful",
                "V1",
                "Free to use",
                new Contact("micro-services-commands", "micro-ci-commands.com", "zakaria.fahraoui@gmail.com"),
                "API License BY micro-ci-commands 1.0",
                "https://micro-ci-commands.com/licenses",
                Collections.emptyList()
        );
    }
}
