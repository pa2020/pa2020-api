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
                .apis(RequestHandlerSelectors.basePackage("fr.esgi.api"))
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Contact Application API",
                "Application Micro-Services with Spring Boot RESTful for analysis sentiment",
                "V2.0",
                "Free to use",
                new Contact("NoticeTracker-analysis-sentiment", "Notice-Tracker.com", "zakaria.fahraoui@gmail.com"),
                "API License BY Notice-Tracker 2.0",
                "https://Notice-Tracker.com/licenses",
                Collections.emptyList()
        );
    }
}
