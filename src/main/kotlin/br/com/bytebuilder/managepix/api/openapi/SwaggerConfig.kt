package br.com.bytebuilder.managepix.api.openapi

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info().title("Manage Pix")
                    .description("Geranciamento de PIX para Estabelecimentos Comerciais")
                    .version("v1")
            )
    }

    // Se você quiser definir grupos de APIs ou controladores específicos
    @Bean
    fun apiGroup(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("ByteBuilder")
            .packagesToScan("br.com.bytebuilder.managepix.api")
            .build()
    }
}