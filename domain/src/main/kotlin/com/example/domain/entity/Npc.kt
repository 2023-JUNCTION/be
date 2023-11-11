package com.example.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class Npc: Character() {
    @Column(name = "user_id")
    var userId: Long = 0
}
