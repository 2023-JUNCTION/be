package com.example.domain.entity

import jakarta.persistence.*
import org.springframework.boot.autoconfigure.domain.EntityScan

@EntityScan
@Entity
class KineticHistory: BaseEntity() {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "kinetic_history_id", nullable = false)
  var id: Long = 0
  @Column(name = "user_id")
  var userId: Long = 0
  @Column(name = "status")
  lateinit var status: MovementStatus
}

enum class MovementStatus {
  STAND, SIT, WALK
}
