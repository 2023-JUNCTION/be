package com.example.client.solum.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpRequest
import org.springframework.http.MediaType
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate

@Configuration
class SolumRestTemplateConfig {
    @Bean
    @Qualifier("solumRestTemplate")
    fun solumRestTemplate(): RestTemplate {
        val restTemplate = RestTemplate()

        val converter = MappingJackson2HttpMessageConverter()
        converter.supportedMediaTypes = listOf(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML)
        converter.defaultCharset = Charsets.UTF_8
        restTemplate.messageConverters = listOf(converter)

        restTemplate.interceptors.add(
            ClientHttpRequestInterceptor {
                    request: HttpRequest, body: ByteArray?, execution: ClientHttpRequestExecution ->
                request.headers.add("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6Ilg1ZVhrNHh5b2pORnVtMWtsMll0djhkbE5QNC1jNTdkTzZRR1RWQndhTmsiLCJ0eXAiOiJKV1QifQ.eyJpZHAiOiJMb2NhbEFjY291bnQiLCJvaWQiOiI1NjlhNzgxNC1iNzczLTQ4ZjgtYTFkZC02ZTc2MzZhOGYyMDYiLCJzdWIiOiI1NjlhNzgxNC1iNzczLTQ4ZjgtYTFkZC02ZTc2MzZhOGYyMDYiLCJuYW1lIjoic2V1bmctMDAiLCJuZXdVc2VyIjpmYWxzZSwiZXh0ZW5zaW9uX0FkbWluQXBwcm92ZWQiOnRydWUsImV4dGVuc2lvbl9DdXN0b21lckNvZGUiOiJKQzA4IiwiZXh0ZW5zaW9uX0N1c3RvbWVyTGV2ZWwiOiIxIiwiZW1haWxzIjpbInNldW5nLTAwQG5hdmVyLmNvbSJdLCJ0ZnAiOiJCMkNfMV9ST1BDX0F1dGgiLCJhenAiOiJlMDhlNTRmZi01YmIxLTRhZTctYWZkZS1iOWNkYzhmYTIzYWUiLCJ2ZXIiOiIxLjAiLCJpYXQiOjE2OTI0ODk1NTMsImF1ZCI6ImUwOGU1NGZmLTViYjEtNGFlNy1hZmRlLWI5Y2RjOGZhMjNhZSIsImV4cCI6MTY5MjU3NTk1MywiaXNzIjoiaHR0cHM6Ly9zb2x1bWIyYy5iMmNsb2dpbi5jb20vYjBjOGUzZDktMDhmYS00ODdhLWFmZjEtODViYWUxMWZjNmM1L3YyLjAvIiwibmJmIjoxNjkyNDg5NTUzfQ.ToEGpfKYZg-tCqK-TJV8t1iqmaf_4zWEdSBgEIQWiUgWgm9DHpzxnggNzcD_6a02jNSkHCyNfykjHBnuUmuSxRP-ew9kRhUJ3tzozOQxO82fa_Wj3of4FRsfLHCf5bMvRxFONi-IPGu0saqt3JRE_OzKSaw0q5PjgfLRjHAuaXZBixHCga6PMH11zZCAa7ff5jdBHZQmgj865NAcUMj3pYH8pPYHUtDGD_zIUztfAOwWVeeDLKrLK6wJYRLGCURLtxIRYm1bQgDnQBXQwYlBm4La0ZIup0Pn-niAyT8Fb1UIH9L8BBLRhzVeEdATBzfg5ZTscKl6XDP-gi7o96fuAA")
                request.headers.add("Content-Type", "application/json")
                request.headers.add("Accept", "application/json")
                request.headers.add("Accept-Charset", "UTF-8")
                execution.execute(request, body!!)
            },
        )
        return restTemplate
    }
}
