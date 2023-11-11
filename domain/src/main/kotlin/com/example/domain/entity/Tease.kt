package com.example.domain.entity

data class Tease(
  val id: String,
  val from: String,
  val to: String,
  val message: String?,
  val mission: Mission?,
): BaseEntity()

enum class Mission(
  val title: String
) {
  SHAKE_IT("흔들어"),
}
