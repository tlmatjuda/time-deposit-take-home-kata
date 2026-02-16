package org.ikigaidigital.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI timeDepositOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Time Deposit API")
                .description("Refactoring kata API for time deposit balance updates and retrieval.")
                .version("v1")
                .license(new License().name("Internal Use")));
    }
}
