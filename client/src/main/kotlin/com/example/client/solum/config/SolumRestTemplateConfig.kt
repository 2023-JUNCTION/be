package com.example.client.solum.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.web.client.RestTemplate

@Configuration
class SolumRestTemplateConfig {
    @Bean
    @Qualifier("solumRestTemplate")
    fun solumRestTemplate(): RestTemplate {
        val restTemplate = RestTemplate()
        restTemplate.interceptors.add(
            ClientHttpRequestInterceptor {
                    request: HttpRequest, body: ByteArray?, execution: ClientHttpRequestExecution ->
                request.headers.add("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6Ilg1ZVhrNHh5b2pORnVtMWtsMll0djhkbE5QNC1jNTdkTzZRR1RWQndhTmsiLCJ0eXAiOiJKV1QifQ.eyJpZHAiOiJMb2NhbEFjY291bnQiLCJvaWQiOiI1NjlhNzgxNC1iNzczLTQ4ZjgtYTFkZC02ZTc2MzZhOGYyMDYiLCJzdWIiOiI1NjlhNzgxNC1iNzczLTQ4ZjgtYTFkZC02ZTc2MzZhOGYyMDYiLCJuYW1lIjoic2V1bmctMDAiLCJuZXdVc2VyIjpmYWxzZSwiZXh0ZW5zaW9uX0FkbWluQXBwcm92ZWQiOnRydWUsImV4dGVuc2lvbl9DdXN0b21lckNvZGUiOiJKQzA4IiwiZXh0ZW5zaW9uX0N1c3RvbWVyTGV2ZWwiOiIxIiwiZW1haWxzIjpbInNldW5nLTAwQG5hdmVyLmNvbSJdLCJ0ZnAiOiJCMkNfMV9ST1BDX0F1dGgiLCJhenAiOiJlMDhlNTRmZi01YmIxLTRhZTctYWZkZS1iOWNkYzhmYTIzYWUiLCJ2ZXIiOiIxLjAiLCJpYXQiOjE2OTI0Mjk0MTUsImF1ZCI6ImUwOGU1NGZmLTViYjEtNGFlNy1hZmRlLWI5Y2RjOGZhMjNhZSIsImV4cCI6MTY5MjUxNTgxNSwiaXNzIjoiaHR0cHM6Ly9zb2x1bWIyYy5iMmNsb2dpbi5jb20vYjBjOGUzZDktMDhmYS00ODdhLWFmZjEtODViYWUxMWZjNmM1L3YyLjAvIiwibmJmIjoxNjkyNDI5NDE1fQ.aEPlBdptnxcM9g65YtRRiaYD7XL9Ho09LjpC94ctu6VIT75WRmAPjZPCNCeGqZJnxGh7pk2mfhOEiCGI1YNb5y92U3OaiVWZ9bm-mOzSc7GzNekRlBqFiK86ElQn2CH6npG8K9BfSdUkcQbWBr9igILLgJUrOoTpYaazsHe9wLV6IXP1exdsIZWdzZuGbzTyhqNXRdEK6PslMd30xiFnw9mBEJIuOBuZrWC56SC5w8fHVxRqiYyFfFVUre4gsPJPUl38UVqfBC7yzCXZSyZu0F8n4T2HHKQPZF8S5jLm1p4vQaXHfU7jVOd4nQIxbjkHVXX_lFRswyMq3MEQxUfzOw")
                execution.execute(request, body!!)
            },
        )
        return restTemplate
    }
}
