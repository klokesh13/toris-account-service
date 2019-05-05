package com.trimble.ttm.toris.account.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket apiDocket(ServletContext servletContext) {

        Contact contact = new Contact(
                "TORIS Dev",
                "https://sites.google.com/trimble.com/swifts/teams/swifts",
                "swiftschennai@trimble.com"
        );

        ApiInfo apiInfo = new ApiInfo(
                "TORIS Account API's",
                "RESTful Web Service on Account endpoints", "0.0.1-SNAPSHOT",
                "https://sites.google.com/trimble.com/swifts/teams/swifts", contact,
                "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());

        Docket docket =  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.trimble.ttm.toris.account.controller"))
                .paths(PathSelectors.any())
                .build();

        return docket;

    }
}
