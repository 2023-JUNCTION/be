package com.example.domain.entity

abstract class Character(
    val id: Long,
    val nickname: String,
    val coordinate: Coordinate,
    val characterId: String,
    val movement: MovementStatus,
    val missionStatus: Boolean,
    val teasedBadges: List<Tease>,
    val connectedCharacterId: String?
): BaseEntity()
