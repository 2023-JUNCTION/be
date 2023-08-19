package com.example.api.client

import com.example.api.dto.PushSolumImageRequest
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
    return restTemplate.getForObject("$baseUrl/version", Object::class.java)
  }

  fun pushLabelImage(labelCode: String, elsImage: String): Object? {
    val request = PushSolumImageRequest(labelCode = labelCode, image = elsImage, page =1, frontPage = 1, articleList = listOf())

    return restTemplate.postForObject("$baseUrl/labels/image?company=JC08&store=1111", request, Object::class.java)
  }
}
