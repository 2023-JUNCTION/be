package com.example.domain.entity

import jakarta.persistence.*
import org.springframework.boot.autoconfigure.domain.EntityScan

@EntityScan
@Entity
@Table(name = "user_table")
class User(
    @Column(name = "nickname") var nickname: String,

    @OneToOne
    @JoinColumn(name = "coordinate_id")
    var coordinate: Coordinate,

    @Column(name = "movement")
    var movement: MovementStatus,

    @Column(name = "mission_status")
    var missionStatus: Boolean = false,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var teases: MutableList<Tease>? = null,

    @Column(name = "connected_user_id")
    var connectedUserId: Long? = null,

    @Column(name = "npc")
    var npc: Boolean = false,

    @Column(name = "user_id_for_npc")
    var userIdForNpc: Long? = null,
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    var id: Long = 0
}
