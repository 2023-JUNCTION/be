package com.example.api.dto

data class CreateEslOrderRequest(
    val orderNumber: Long,
    val labelCode: String,
)
