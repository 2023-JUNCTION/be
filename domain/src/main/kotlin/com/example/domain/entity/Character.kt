package com.example.domain.entity

abstract class Character(
    val id: String,
    val nickname: String,
    val coordinate: Coordinate,
    val characterId: String,
    val stand: Boolean,
    val missionStatus: Boolean,
    val teasedBadges: List<Tease>,
    val conntectedCharacterId: String?
)

data class Coordinate(
    val latitude: Double,
    val longitude: Double
)

data class Tease(
    val id: String,
    val from: String,
    val to: String,
    val message: String?,
    val mission: Mission?,
    val createdAt: String
)

enum class Mission(
    val title: String
) {
    SHAKE_IT("흔들어"),
}
