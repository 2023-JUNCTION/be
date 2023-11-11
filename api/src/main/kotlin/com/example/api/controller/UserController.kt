package com.example.api.controller

import com.example.api.dto.CreateUserResponse
import com.example.domain.repository.UserRepository
import com.example.domain.entity.Coordinate
import com.example.domain.entity.Mission
import com.example.domain.entity.MovementStatus
import com.example.domain.entity.Tease
import com.example.domain.entity.User
import com.example.domain.repository.CoordinateRepository
import com.example.domain.repository.TeaseRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["*"])
@Tag(name = "", description = "")
class UserController(
    private val userRepository: UserRepository,
    private val coordinateRepository: CoordinateRepository,
    private val teaseRepository: TeaseRepository
) {
    @Operation(description = "게스트 로그인 시 User 생성 (default 데이터 세팅 O)")
    @PostMapping("/")
    fun createUser(): CreateUserResponse {
        val initCoordinate = Coordinate(
            latitude = 0.0,
            longitude = 0.0
        )
        val saved = coordinateRepository.save(initCoordinate)

        val newUser = User(
            nickname = "guest",
            coordinate = saved,
            movement = MovementStatus.STAND,
            missionStatus = false,
            teases = mutableListOf(),
            connectedUserId = null,
            npc = false,
            userIdForNpc = null
        )

        val savedUser = userRepository.save(newUser)

        val defaultTeases = mutableListOf(
            teaseRepository.save(
                Tease(
                    from = 1,
                    to = savedUser.id,
                    message = "메롱 👅",
                )
            ),
            teaseRepository.save(
                Tease(
                    from = 2,
                    to = savedUser.id,
                    mission = Mission.SHAKE_IT,
                )
            )
        )

        savedUser.teases = defaultTeases

        val save = userRepository.save(savedUser)
        return CreateUserResponse(
            id = save.id,
        )
    }
}
