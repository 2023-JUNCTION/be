package com.example.domain.entity

import jakarta.persistence.*
import org.springframework.boot.autoconfigure.domain.EntityScan

@EntityScan
@Entity
@Table(name = "user_table")
class User: BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
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

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    lateinit var teases: List<Tease>

    @Column(name = "connected_user_id")
    var connectedUserId: Long? = null

    @Column(name = "npc")
    var npc: Boolean = false

    @Column(name = "user_id_for_npc")
    var userIdForNpc: Long? = null
}
