package com.example.domain.entity

import com.example.domain.entity.Character
import jakarta.persistence.*

@Entity
class Tease: BaseEntity() {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "tease_id", nullable = false)
  var id: Long = 0

  @Column(name = "from")
  var from: String = ""

  @Column(name = "to")
  var to: String = ""

  @Column(name = "message")
  var message: String? = null

  @Column(name = "mission")
  var mission: Mission? = null

  @ManyToOne
  @JoinColumn(name = "character_id")
  lateinit var character: Character
    protected set
}

enum class Mission(
  val title: String
) {
  SHAKE_IT("흔들어"),
}
