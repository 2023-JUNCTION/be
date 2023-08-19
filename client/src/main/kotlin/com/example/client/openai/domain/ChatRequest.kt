package com.example.client.openai.domain

class ChatRequest(
    val model: String,
    val messages: List<Message>
) {
    private val temperature = 0.0
}


