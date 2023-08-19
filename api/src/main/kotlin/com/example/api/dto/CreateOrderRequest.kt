package com.example.api.dto

data class CreateOrderRequest(
    val menus: List<MenuDto>,
    val eslImage: String,
)

class MenuDto(
    val id: Long,
    val count: Int,
)
