package com.example.api.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
        .components(Components())
        .info(apiInfo())

    private fun apiInfo() = Info()
        .title("Springdoc 테스트")
        .description("Springdoc을 사용한 Swagger UI 테스트")
        .version("1.0.0")
}
