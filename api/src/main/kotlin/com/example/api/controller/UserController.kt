package com.example.api.controller

import com.example.domain.dto.TeaseDto
import com.example.domain.dto.toDto
import com.example.domain.entity.*
import com.example.domain.repository.UserRepository
import com.example.domain.repository.CoordinateRepository
import com.example.domain.repository.KineticHistoryRepository
import com.example.domain.repository.TeaseRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["*"])
@Tag(name = "유저 API", description = "로그인, 상태 등 유저 관련")
class UserController(
    private val userRepository: UserRepository,
    private val coordinateRepository: CoordinateRepository,
    private val teaseRepository: TeaseRepository,
    private val kineticHistoryRepository: KineticHistoryRepository
) {
    @Operation(description = "게스트 로그인 시 User 생성 (default 데이터 세팅 O)")
    @PostMapping("/user")
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

        val tease1 = teaseRepository.save(
            Tease(
                from = 1,
                to = savedUser.id,
                message = "메롱!!",
                user = savedUser
            )
        )

        val tease2 = teaseRepository.save(
            Tease(
                from = 2,
                to = savedUser.id,
                mission = Mission.SHAKE_IT,
                user = savedUser
            )
        )

        savedUser.teases = mutableListOf(tease1, tease2)

        val save = userRepository.save(savedUser)
        return CreateUserResponse(
            id = save.id,
        )

        //? npc 5개 생성하고 응답에 id 값을 포함시켜줘야 한다
    }

    @Operation(description = "유저 상태 업데이트")
    @PostMapping("/status")
    fun updateUser(@RequestBody request: UpdateUserRequest): UpdateUserResponse {
        val user = userRepository.findById(request.userId).orElseThrow()

        val coordinate = coordinateRepository.save(
            Coordinate(
                latitude = request.coordinate.latitude,
                longitude = request.coordinate.longitude
            )
        )

        user.coordinate = coordinate
        kineticHistoryRepository.save(
            KineticHistory.of(
                userId = user.id,
                status = request.movement
            )
        )

        val kineticHistories = kineticHistoryRepository.findAllByUserId(user.id)

        KineticHistories(kineticHistories).defineMovementStatus()?.let {
            user.movement = it
        }

        val savedUser = userRepository.save(user)

        return UpdateUserResponse(
            userId = savedUser.id,
            nickname = savedUser.nickname,
            coordinate = CoordinateDto(
                latitude = savedUser.coordinate.latitude,
                longitude = savedUser.coordinate.longitude
            ),
            movement = savedUser.movement,
            missionStatus = savedUser.missionStatus,
            teases = savedUser.teases.map { it.toDto() },
            connectedUserId = savedUser.connectedUserId,
            npc = savedUser.npc,
            userIdForNpc = savedUser.userIdForNpc
        )
    }
}

data class CreateUserResponse(
    val id: Long,
)

data class UpdateUserRequest(
    val userId: Long,
    val coordinate: CoordinateDto,
    val movement: MovementStatus,
)

data class UpdateUserResponse(
    val userId: Long,
    val nickname: String,
    val coordinate: CoordinateDto,
    val movement: MovementStatus,
    val missionStatus: Boolean,
    val teases: List<TeaseDto>,
    val connectedUserId: Long?,
    val npc: Boolean,
    val userIdForNpc: Long?
)

data class CoordinateDto(
    val latitude: Double,
    val longitude: Double
)
