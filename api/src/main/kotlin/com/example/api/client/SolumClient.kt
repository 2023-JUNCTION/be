package com.example.api.client

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class SolumClient {
  @Qualifier("solumRestTemplate")
  @Autowired
  lateinit var restTemplate: RestTemplate

  @Value("\${solum.api.url}")
  lateinit var baseUrl: String

  fun getVersion(): Object? {
    return restTemplate.getForObject("$baseUrl/common/api/v2/common/version", Object::class.java)
  }
}
