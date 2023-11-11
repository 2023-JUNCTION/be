package com.example.domain.dto

import com.example.domain.entity.Mission
import com.example.domain.entity.Tease

data class TeaseDto(
  val from: Long,
  val to: Long,
  val message: String?,
  val mission: Mission?,
  val userId: Long?,
)

fun Tease.toDto() = TeaseDto(
  from = this.from,
  to = this.to,
  message = this.message,
  mission = this.mission,
  userId = this.user?.id,
)
