package com.example.api.controller

import com.example.api.dto.ResultResponse
import com.example.domain.entity.Mission
import com.example.domain.entity.Tease
import com.example.domain.repository.CoordinateRepository
import com.example.domain.repository.TeaseRepository
import com.example.domain.repository.UserRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["*"])
@Tag(name = "Tease API", description = "")
class UserTeaseController(
    private val userRepository: UserRepository,
    private val coordinateRepository: CoordinateRepository,
    private val teaseRepository: TeaseRepository
) {
    @Operation(description = "[데모용] 유저가 npc 하나 선택해서 Tease 요청")
    @PostMapping("/tease")
    fun request(@RequestBody request: TeaseRequest): ResultResponse {
        val userId = request.userId
        val npcId = request.npcId

        val user = userRepository.findById(userId).orElseThrow()
        val npcList = userRepository.findAllByNpcAndUserIdForNpc(true, userId)

        // npcList에 npcId가 존재하지 않으면 예외 처리
        if (npcList.find { it.id == npcId } == null) throw Exception("해당 유저에 npcId $npcId 를 가진 npc가 존재하지 않음")

        // 해당 npc에 tease를 추가한다
        val npc = userRepository.findById(npcId).orElseThrow()
        val newTease = teaseRepository.save(Tease(from = userId, to = npcId, user = user))
        npc.teases.add(newTease)

        userRepository.save(npc)

        return ResultResponse(true)
    }

    @Operation(description = "특정 Tease 삭제")
    @DeleteMapping("/tease/{teaseId}")
    fun delete(@PathVariable teaseId: Long): ResultResponse {
        teaseRepository.deleteById(teaseId)
        return ResultResponse(true)
    }
}

data class TeaseRequest(
    val userId: Long,
    val npcId: Long,
    val message: String? = null,
    val mission: Mission? = null,
)