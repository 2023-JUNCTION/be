package com.example.domain.entity

data class KineticHistories(
  val kineticHistories: List<KineticHistory>
) {
  fun defineMovementStatus(): MovementStatus? {
    val recent = kineticHistories.take(10)

    if (recent.count { it.status == MovementStatus.STAND } > 5) {
      return MovementStatus.STAND
    }

    if (recent.count { it.status == MovementStatus.SIT } > 5) {
      return MovementStatus.SIT
    }

    if (recent.count { it.status == MovementStatus.WALK } > 5) {
      return MovementStatus.WALK
    }

    return null
  }
}
