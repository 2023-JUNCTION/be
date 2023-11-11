package com.example.api.controller

import com.example.api.dto.ResultResponse
import com.example.domain.repository.CoordinateRepository
import com.example.domain.repository.TeaseRepository
import com.example.domain.repository.UserRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["*"])
@Tag(name = "미션 API", description = "Tease의 미션 관련")
class MissionController(
    private val userRepository: UserRepository,
    private val coordinateRepository: CoordinateRepository,
    private val teaseRepository: TeaseRepository
) {
    @Operation(description = "미션 시작")
    @PostMapping("/{userId}/mission/{teaseId}")
    fun start(
        @PathVariable userId: Long,
        @PathVariable teaseId: Long
    ): ResultResponse {
        val user = userRepository.findById(userId).orElseThrow()
        val tease = teaseRepository.findById(teaseId).orElseThrow()

        if (user.missionStatus) throw Exception("유저의 missionStatus가 이미 true")

        // 해당 유저의 Tease 미션 시작
        user.missionStatus = true

        userRepository.save(user)

        return ResultResponse(true)
    }

    @Operation(description = "미션 종료")
    @DeleteMapping("/{userId}/mission/{teaseId}")
    fun done(
        @PathVariable userId: Long,
        @PathVariable teaseId: Long
    ): ResultResponse {
        val user = userRepository.findById(userId).orElseThrow()
        val tease = teaseRepository.findById(teaseId).orElseThrow()

        if (!user.missionStatus) throw Exception("유저의 missionStatus가 이미 false")

        // 요청한 유저의 미션 진행 상태를 종료로 만든다
        user.missionStatus = false
        user.teases = mutableListOf()

        // 요청한 유저의 모든 Tease 딱지를 삭제해준다
        val allByUserId = teaseRepository.findAllByUserId(userId)
        teaseRepository.deleteAllById(allByUserId.map { it.id })

        userRepository.save(user)

        return ResultResponse(true)
    }
}
