package com.example.api.dto

data class CreateOrderRequest(
    val orders: List<OrderDto>,
    val elsImage: String,
)

class OrderDto(
    val id: Long,
    val count: Int,
)
