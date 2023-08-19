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
                request.headers.add("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6Ilg1ZVhrNHh5b2pORnVtMWtsMll0djhkbE5QNC1jNTdkTzZRR1RWQndhTmsiLCJ0eXAiOiJKV1QifQ.eyJpZHAiOiJMb2NhbEFjY291bnQiLCJvaWQiOiI1NjlhNzgxNC1iNzczLTQ4ZjgtYTFkZC02ZTc2MzZhOGYyMDYiLCJzdWIiOiI1NjlhNzgxNC1iNzczLTQ4ZjgtYTFkZC02ZTc2MzZhOGYyMDYiLCJuYW1lIjoic2V1bmctMDAiLCJuZXdVc2VyIjpmYWxzZSwiZXh0ZW5zaW9uX0FkbWluQXBwcm92ZWQiOnRydWUsImV4dGVuc2lvbl9DdXN0b21lckNvZGUiOiJKQzA4IiwiZXh0ZW5zaW9uX0N1c3RvbWVyTGV2ZWwiOiIxIiwiZW1haWxzIjpbInNldW5nLTAwQG5hdmVyLmNvbSJdLCJ0ZnAiOiJCMkNfMV9ST1BDX0F1dGgiLCJhenAiOiJlMDhlNTRmZi01YmIxLTRhZTctYWZkZS1iOWNkYzhmYTIzYWUiLCJ2ZXIiOiIxLjAiLCJpYXQiOjE2OTI0NDcxMTgsImF1ZCI6ImUwOGU1NGZmLTViYjEtNGFlNy1hZmRlLWI5Y2RjOGZhMjNhZSIsImV4cCI6MTY5MjUzMzUxOCwiaXNzIjoiaHR0cHM6Ly9zb2x1bWIyYy5iMmNsb2dpbi5jb20vYjBjOGUzZDktMDhmYS00ODdhLWFmZjEtODViYWUxMWZjNmM1L3YyLjAvIiwibmJmIjoxNjkyNDQ3MTE4fQ.N0vR6sxibMtps-vSpZ1V4XYj_LcBdgw1ZjMON-xx--WvFHAgNk_c_gTIvI9Trx8eD3bUM5Stp0lKHY25L-kzi8hhZYzX74oXeaIAgjHnPmcmQEZ9w_QlrGyZZk3IbVoS8sbyTOgCQvmxNNPUDcoJMAI-SS9o3YiYdniY0Rn2-hxDB_EGpTTpkuLjwNxyxERNZbQOCbL4NOkhZJWvz1IwrLzGCtHuu0oFEYW1Uua5_kwAADTTJBF8XGw2N8AzTywFXC7gsqbbp-84Ihh0u9howhBZSNDDrFBJ6024dYpCxaikNQQy3Sp_t__7rP2vTezgeL3OFp9Z5SvRTpYIOgdfng")
                execution.execute(request, body!!)
            },
        )
        return restTemplate
    }
}
