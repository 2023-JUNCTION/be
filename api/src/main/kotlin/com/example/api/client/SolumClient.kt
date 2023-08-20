package com.example.api.client

import com.example.api.dto.Color
import com.example.api.dto.PushSolumImageRequest
import com.example.api.dto.PushSolumLedRequest
import com.example.domain.repository.EslOrderRepository
import com.example.domain.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class SolumClient(
    val elsOrderRepository: EslOrderRepository,
    val orderRepository: OrderRepository,
) {
    @Qualifier("solumRestTemplate")
    @Autowired
    lateinit var restTemplate: RestTemplate

    @Value("\${solum.api.url}")
    lateinit var baseUrl: String

    fun getVersion(): Any? {
        return restTemplate.getForObject("$baseUrl/v2/common/version", Object::class.java)
    }

    fun pushLed(labelCode: String, color: Color): Any? {
        val request = PushSolumLedRequest(
            labelCode = labelCode,
            color = color,
            duration = DEFAULT_DURATION,
        )

        return restTemplate.put("$baseUrl/v1/labels/contents/led?company=JC08", listOf(request))
    }

    fun pushLabelImage(labelCode: String, elsImage: String): Any? {
        val request = PushSolumImageRequest(labelCode = labelCode, image = elsImage, page = 1, frontPage = 1, articleList = listOf())

        return restTemplate.postForObject("$baseUrl/v2/common/labels/image?company=JC08&store=1111", request, Any::class.java)
    }

    companion object {
        private const val DEFAULT_DURATION: String = "10s"
    }
}
