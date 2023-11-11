package com.example.domain.entity

import org.springframework.data.jpa.domain.AbstractPersistable_.id

data class User(
    val id: String,
    val nickname: String,
    val coordinate: Coordinate,
    val characterId: String,
    val stand: Boolean, //t: 서있음, f: 걷고있음
    val missionStatus: Boolean, //t: 미션 진행중, f: 미션 예정
    val teasedBadges: List<Tease>,
    val conntectedCharacterId: String? //내가 지금 가고 있는 목적지 친구
) : Character(

)
