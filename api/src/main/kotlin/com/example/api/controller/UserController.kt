package com.example.api.controller

import com.example.domain.dto.TeaseDto
import com.example.domain.dto.toDto
import com.example.domain.entity.*
import com.example.domain.repository.CoordinateRepository
import com.example.domain.repository.KineticHistoryRepository
import com.example.domain.repository.TeaseRepository
import com.example.domain.repository.UserRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/user")
@Tag(name = "유저 API", description = "로그인, 상태 등 유저 관련")
class UserController(
    private val userRepository: UserRepository,
    private val coordinateRepository: CoordinateRepository,
    private val teaseRepository: TeaseRepository,
    private val kineticHistoryRepository: KineticHistoryRepository
) {
    @Operation(description = "게스트 로그인 시 User 생성 (default 데이터 세팅 O)")
    @PostMapping
    fun createUser(@RequestParam latitude: Double, @RequestParam longitude: Double): CreateUserResponse {
        val initCoordinate = Coordinate(
            latitude = latitude,
            longitude = longitude
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

        val tease3 = teaseRepository.save(
            Tease(
                from = 3,
                to = savedUser.id,
                mission = Mission.JUMPING_JACK,
                user = savedUser
            )
        )

        savedUser.teases = mutableListOf(tease1, tease2, tease3)

        val save = userRepository.save(savedUser)

        return CreateUserResponse(
            id = save.id,
            npcList = createNpcForUser(save.id)
        )
    }

    private fun createNpcForUser(userId: Long): List<User> {
        val coordinate1 = coordinateRepository.save(
            Coordinate(
                latitude = 60.160175885706956,
                longitude = 24.914537342140076
            )
        )

        val coordinate2 = coordinateRepository.save(
            Coordinate(
                latitude = 60.16466263018458,
                longitude = 24.907802541273565
            )
        )

        val coordinate3 = coordinateRepository.save(
            Coordinate(
                latitude = 60.163688688766165,
                longitude = 24.95200162882376
            )
        )

        val coordinate4 = coordinateRepository.save(
            Coordinate(
                latitude = 60.16342281196804,
                longitude = 24.915230001448272
            )
        )

        val coordinate5 = coordinateRepository.save(
            Coordinate(
                latitude = 60.16247160462817,
                longitude = 24.90645402677947
            )
        )

        val npc1 = User(
            nickname = "npc1",
            coordinate = coordinate1,
            movement = MovementStatus.STAND,
            missionStatus = false,
            teases = mutableListOf(),
            connectedUserId = null,
            npc = true,
            userIdForNpc = userId
        )
        val npc2 = User(
            nickname = "npc2",
            coordinate = coordinate2,
            movement = MovementStatus.SIT,
            missionStatus = false,
            teases = mutableListOf(),
            connectedUserId = null,
            npc = true,
            userIdForNpc = userId
        )
        val npc3 = User(
            nickname = "npc3",
            coordinate = coordinate3,
            movement = MovementStatus.WALK,
            missionStatus = false,
            teases = mutableListOf(),
            connectedUserId = null,
            npc = true,
            userIdForNpc = userId
        )
        val npc4 = User(
            nickname = "npc4",
            coordinate = coordinate4,
            movement = MovementStatus.SIT,
            missionStatus = false,
            teases = mutableListOf(),
            connectedUserId = null,
            npc = true,
            userIdForNpc = userId
        )
        val npc5 = User(
            nickname = "npc5",
            coordinate = coordinate5,
            movement = MovementStatus.STAND,
            missionStatus = false,
            teases = mutableListOf(),
            connectedUserId = null,
            npc = true,
            userIdForNpc = userId
        )

        val savedNpc1 = userRepository.save(npc1)
        val savedNpc2 = userRepository.save(npc2)
        val savedNpc3 = userRepository.save(npc3)
        val savedNpc4 = userRepository.save(npc4)
        val savedNpc5 = userRepository.save(npc5)
        // 이 중 데모에서 인터랙션은 2명, 나머지 3명은 놀고 있음

        return listOf(savedNpc1, savedNpc2, savedNpc3, savedNpc4, savedNpc5)
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
    val npcList: List<User>
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
