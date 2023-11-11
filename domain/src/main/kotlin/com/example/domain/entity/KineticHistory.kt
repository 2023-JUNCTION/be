package com.example.domain.entity

data class KineticHistory(
  val id: Long,
  val userId: Long,
  val status: MovementStatus,
): BaseEntity()

enum class MovementStatus {
  STAND, SIT, WALK
}
