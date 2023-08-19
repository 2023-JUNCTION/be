package com.example.api.controller

import com.example.client.openai.domain.ChatRequest
import com.example.client.openai.domain.ChatResponse
import com.example.client.openai.domain.Message
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@Tag(name = "openai gpt와 대화하는 API", description = "chatgpt 컨트롤러")
class ChatController {

    @Qualifier("openaiRestTemplate")
    @Autowired
    private val restTemplate: RestTemplate? = null

    @Value("\${openai.model}")
    private val model: String? = null

    @Value("\${openai.api.url}")
    private val apiUrl: String? = null

    @Operation(description = "간단한 대화")
    @GetMapping("/chat")
    fun chat(@RequestParam prompt: String): String {
        val request = ChatRequest(model!!, listOf(Message("user", prompt)))

        val response = restTemplate!!.postForObject(apiUrl!!, request, ChatResponse::class.java)
        return if (response?.choices == null || response.choices.isEmpty()) {
            "No response"
        } else {
            response.choices[0].message.content
        }
    }
}
