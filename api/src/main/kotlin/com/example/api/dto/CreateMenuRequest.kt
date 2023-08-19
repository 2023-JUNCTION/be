package com.example.api.dto

class CreateMenuRequest(
    val menuName: String,
    val menuPrice: Int,
    val description: String? = null,
    val imageUrl: String? = null,
)
