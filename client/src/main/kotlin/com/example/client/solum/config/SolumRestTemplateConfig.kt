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
                request.headers.add("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6Ilg1ZVhrNHh5b2pORnVtMWtsMll0djhkbE5QNC1jNTdkTzZRR1RWQndhTmsiLCJ0eXAiOiJKV1QifQ.eyJpZHAiOiJMb2NhbEFjY291bnQiLCJvaWQiOiI1NjlhNzgxNC1iNzczLTQ4ZjgtYTFkZC02ZTc2MzZhOGYyMDYiLCJzdWIiOiI1NjlhNzgxNC1iNzczLTQ4ZjgtYTFkZC02ZTc2MzZhOGYyMDYiLCJuYW1lIjoic2V1bmctMDAiLCJuZXdVc2VyIjpmYWxzZSwiZXh0ZW5zaW9uX0FkbWluQXBwcm92ZWQiOnRydWUsImV4dGVuc2lvbl9DdXN0b21lckNvZGUiOiJKQzA4IiwiZXh0ZW5zaW9uX0N1c3RvbWVyTGV2ZWwiOiIxIiwiZW1haWxzIjpbInNldW5nLTAwQG5hdmVyLmNvbSJdLCJ0ZnAiOiJCMkNfMV9ST1BDX0F1dGgiLCJhenAiOiJlMDhlNTRmZi01YmIxLTRhZTctYWZkZS1iOWNkYzhmYTIzYWUiLCJ2ZXIiOiIxLjAiLCJpYXQiOjE2OTI0NzgzMjQsImF1ZCI6ImUwOGU1NGZmLTViYjEtNGFlNy1hZmRlLWI5Y2RjOGZhMjNhZSIsImV4cCI6MTY5MjU2NDcyNCwiaXNzIjoiaHR0cHM6Ly9zb2x1bWIyYy5iMmNsb2dpbi5jb20vYjBjOGUzZDktMDhmYS00ODdhLWFmZjEtODViYWUxMWZjNmM1L3YyLjAvIiwibmJmIjoxNjkyNDc4MzI0fQ.nanvGZI6mJT8ODgU7OOmVOkb4FMooGgLb2K84BmZjMn0lnHqr_pvocKv5lO7W3ieXQMoocUryfQFgxUGXLwJ-y-Anro4bqy4honDYIA87_WQ32ih1O7L8YadT_G3q9bZCslLO8pgrKiQLGLXliM-t-DqD6CauSW-t_6b3Id5bGabdoyrom4achtjo4mQyyhyFzi40Tl4KfrCo9avI_50nYVQkScAgcD6MlGclJX5qWE6NVVkQiD88f2U9X5boW8Glm8D6Xqy3w2gjV9NUlUjuv0INd_g1pj8kOF46CbEVLU5BArcgdkoF_RJBCvpoGQ7bL_5Jb_GplfIFuKmsBB_ug")
                execution.execute(request, body!!)
            },
        )
        return restTemplate
    }
}
