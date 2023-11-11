package com.example.domain.entity

class Npc(
    id: Long,
    nickname: String,
    coordinate: Coordinate,
    characterId: String,
    movement: MovementStatus,
    missionStatus: Boolean,
    teasedBadges: List<Tease>,
    connectedCharacterId: String?,
    userId: Long,
): Character(
    id = id,
    nickname = nickname,
    coordinate = coordinate,
    characterId = characterId,
    movement = movement,
    missionStatus = missionStatus,
    teasedBadges = teasedBadges,
    connectedCharacterId = connectedCharacterId,
)
