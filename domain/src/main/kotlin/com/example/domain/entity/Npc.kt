package com.example.domain.entity

data class Npc(
    val id: String,
    val nickname: String,
    val coordinate: Coordinate,
    val characterId: String,
    val isWalking: Boolean,
    val isMissionStarted: Boolean,
    val isMissionFinished: Boolean,
    val myTizingBadge: List<TizingBadge>,
    val myTizedBadge: List<TizedBadge>,
    val myHighFiveStatus: Boolean
) : Character(
    id,
    nickname,
    coordinate,
    characterId,
    isWalking,
    isMissionStarted,
    isMissionFinished,
    myTizingBadge,
    myTizedBadge,
    myHighFiveStatus
)
