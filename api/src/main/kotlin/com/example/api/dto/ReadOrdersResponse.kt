package com.example.api.dto

data class ReadOrdersResponse(
    val orders: List<ReadOrderResponse>,
)

data class ReadOrderResponse(
    val id: Long,
    val orderMenu: List<OrderMenuResponse>,
    val done: Boolean,
    val requiredTime: Long,
    val eslOrderNumber: Int,
)

data class OrderMenuResponse(
    val id: Long,
    val menuId: Long,
    val menuName: String,
    val menuCount: Int,
)
