package com.example.domain.entity

import org.springframework.data.jpa.domain.AbstractPersistable_.id

class User(
    id: Long,
    nickname: String,
    coordinate: Coordinate,
    characterId: String,
    movement: MovementStatus,
    missionStatus: Boolean,
    teasedBadges: List<Tease>,
    connectedCharacterId: String?
): Character(
    id = id,
    nickname = nickname,
    coordinate= coordinate,
    characterId= characterId,
    movement= movement,
    missionStatus= missionStatus,
    teasedBadges= teasedBadges,
    connectedCharacterId= connectedCharacterId,
)
