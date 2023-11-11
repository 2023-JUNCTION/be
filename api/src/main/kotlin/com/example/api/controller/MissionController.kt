package com.example.api.controller

import com.example.api.dto.ResultResponse
import com.example.domain.repository.CoordinateRepository
import com.example.domain.repository.TeaseRepository
import com.example.domain.repository.UserRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["*"])
@Tag(name = "", description = "")
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

        user.missionStatus = true

        userRepository.save(user)

        return ResultResponse(true)
    }
}
