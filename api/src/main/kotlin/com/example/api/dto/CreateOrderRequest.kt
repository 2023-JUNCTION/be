package com.example.api.dto

data class CreateOrderRequest(
    val tableNumber: Int = 1,
    val menus: List<MenuDto>,
    var eslImage: String, // 영수증 이미지
)

class MenuDto(
    val id: Long,
    val count: Int,
)
