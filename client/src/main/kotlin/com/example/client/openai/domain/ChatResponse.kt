package com.example.client.openai.domain

class ChatResponse(
    val choices: List<Choice>,
)

class Choice(
    val message: Message,
    val index: Int,
)
