package com.example.api.dto

data class ReadOrdersResponse(
    val orders: List<OrderResponse>
)

data class OrderResponse(
  val id: Long,
  val orderMenu: List<OrderMenuResponse>,
  val done: Boolean,
)

data class OrderMenuResponse(
  val id: Long,
  val menuId: Long,
  val menuName: String,
  val menuCount: Int,
)
