package com.example.client.solum.config

import org.springframework.beans.factory.annotation.Qualifier
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
                request.headers.add("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6Ilg1ZVhrNHh5b2pORnVtMWtsMll0djhkbE5QNC1jNTdkTzZRR1RWQndhTmsiLCJ0eXAiOiJKV1QifQ.eyJpZHAiOiJMb2NhbEFjY291bnQiLCJvaWQiOiI1NjlhNzgxNC1iNzczLTQ4ZjgtYTFkZC02ZTc2MzZhOGYyMDYiLCJzdWIiOiI1NjlhNzgxNC1iNzczLTQ4ZjgtYTFkZC02ZTc2MzZhOGYyMDYiLCJuYW1lIjoic2V1bmctMDAiLCJuZXdVc2VyIjpmYWxzZSwiZXh0ZW5zaW9uX0FkbWluQXBwcm92ZWQiOnRydWUsImV4dGVuc2lvbl9DdXN0b21lckNvZGUiOiJKQzA4IiwiZXh0ZW5zaW9uX0N1c3RvbWVyTGV2ZWwiOiIxIiwiZW1haWxzIjpbInNldW5nLTAwQG5hdmVyLmNvbSJdLCJ0ZnAiOiJCMkNfMV9ST1BDX0F1dGgiLCJhenAiOiJlMDhlNTRmZi01YmIxLTRhZTctYWZkZS1iOWNkYzhmYTIzYWUiLCJ2ZXIiOiIxLjAiLCJpYXQiOjE2OTI0NDI1NTEsImF1ZCI6ImUwOGU1NGZmLTViYjEtNGFlNy1hZmRlLWI5Y2RjOGZhMjNhZSIsImV4cCI6MTY5MjUyODk1MSwiaXNzIjoiaHR0cHM6Ly9zb2x1bWIyYy5iMmNsb2dpbi5jb20vYjBjOGUzZDktMDhmYS00ODdhLWFmZjEtODViYWUxMWZjNmM1L3YyLjAvIiwibmJmIjoxNjkyNDQyNTUxfQ.HCiUZQsHs2PcnVSGZQD7DCWCGcYkP5e9y0aluuz9fLIdVuE4oQnlPRgRTIyX-ZJq4IfOifnJx_PBhCWhUq6yqruSyDqHM58CI4wnA66oCZ7U4Zwb_hx4BbdwZvrkyn0XgwmLQ99E9eZa6d7r36gOnm6zOsFThPOZrO96KwP9dt-it2LfGzcs7ZbG4kw1JQjt-bxAAl62VvoFZuImUh2fj7VEd3JvFumL2ZnQKlDIfkKwUyjfCXi2O69Tsj9ct7xqdtaHZkWqGWVQpddsdAX46vW2t9b7ww6AbnZ2u2S5r8-lad-49y_M3vlJoeyXB9uO6OY-U_WXPU_HOwwOHr-gKA")
                execution.execute(request, body!!)
            },
        )
        return restTemplate
    }
}
