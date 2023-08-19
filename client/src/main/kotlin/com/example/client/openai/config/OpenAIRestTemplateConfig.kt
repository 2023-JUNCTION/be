package com.example.client.openai.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.web.client.RestTemplate

@Configuration
class OpenAIRestTemplateConfig {
    @Value("\${openai.api.key}")
    private val openaiApiKey: String? = null

    @Value("\${openai.api.organization}")
    private val openaiOrganization: String? = null

    @Bean
    @Qualifier("openaiRestTemplate")
    fun openaiRestTemplate(): RestTemplate {
        val restTemplate = RestTemplate()
        restTemplate.interceptors.add(
            ClientHttpRequestInterceptor {
                    request: HttpRequest, body: ByteArray?, execution: ClientHttpRequestExecution ->
                request.headers.add("Authorization", "Bearer $openaiApiKey")
                request.headers.add("OpenAI-Organization", "$openaiOrganization")
                execution.execute(request, body!!)
            },
        )
        return restTemplate
    }
}
