package com.fse2.lms.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi api(){
        return GroupedOpenApi.builder()
                .group("LMS-V1.0")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    private OpenAPI apiInfo(){
        return new OpenAPI()
                .info(new Info().title("Learning Management System User Registration API documentation")
                        .description("This API contains registration of users"));
    }


}
