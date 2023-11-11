package com.example.domain.entity

import jakarta.persistence.*
import org.springframework.boot.autoconfigure.domain.EntityScan

@EntityScan
@Entity
@Table(name = "character_table")
class Character: BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id", nullable = false)
    var id: Long = 0

    @Column(name = "nickname")
    var nickname: String = ""

    @OneToOne
    @JoinColumn(name = "coordinate_id")
    lateinit var coordinate: Coordinate

    @Column(name = "movement")
    lateinit var movement: MovementStatus

    @Column(name = "mission_status")
    var missionStatus: Boolean = false

    @OneToMany(mappedBy = "character", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    lateinit var teases: List<Tease>

    @Column(name = "connected_character_id")
    var connectedCharacterId: Long? = null
}
