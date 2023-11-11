package com.example.domain.entity

import jakarta.persistence.*
import org.springframework.boot.autoconfigure.domain.EntityScan

@EntityScan
@Entity
class Tease(
    @Column(name = "from_user")
    var from: Long = 0,

    @Column(name = "to_user")
    var to: Long = 0,

    @Column(name = "message")
    var message: String? = null,

    @Column(name = "mission")
    var mission: Mission? = null,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User? = null,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tease_id", nullable = false)
    var id: Long = 0
}

enum class Mission(
    val title: String
) {
    SHAKE_IT("흔들어"),
    JUMPING_JACK("팔벌려뛰기"),
    WALK("산책")
}
