package com.example.api.controller

import com.example.api.dto.ResultResponse
import com.example.domain.repository.CoordinateRepository
import com.example.domain.repository.TeaseRepository
import com.example.domain.repository.UserRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["*"])
@Tag(name = "Take API", description = "")
class UserTakeController(
    private val userRepository: UserRepository,
    private val coordinateRepository: CoordinateRepository,
    private val teaseRepository: TeaseRepository
) {
    @Operation(description = "[데모용] 유저가 npc 하나 선택해서 Take 요청")
    @PostMapping("/take")
    fun request(@RequestBody request: TakeRequest): ResultResponse {
        val userId = request.userId
        val npcId = request.npcId

        val user = userRepository.findById(userId).orElseThrow()
        val npcList = userRepository.findAllByNpcAndUserIdForNpc(true, userId)

        // npcList에 npcId가 존재하지 않으면 예외 처리
        if (npcList.find { it.id == npcId } == null) throw Exception("해당 유저에 npcId $npcId 를 가진 npc가 존재하지 않음")

        // 해당 유저가 npc를 향해 간다
        user.connectedUserId = npcId

        userRepository.save(user)

        return ResultResponse(true)
    }
}

data class TakeRequest(
    val userId: Long,
    val npcId: Long
)
