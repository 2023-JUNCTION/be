package com.example.api.controller

import com.example.api.dto.ResultResponse
import com.example.domain.repository.CoordinateRepository
import com.example.domain.repository.TeaseRepository
import com.example.domain.repository.UserRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["*"])
@Tag(name = "Admin API", description = "어드민, 데모, 관리 등")
class AdminController(
    private val userRepository: UserRepository,
    private val coordinateRepository: CoordinateRepository,
    private val teaseRepository: TeaseRepository,
    private val userController: UserController
) {
    @Operation(description = "전체 유저 조회")
    @GetMapping("/admin/all-user")
    fun allUser(): Any {
        return userRepository.findAll()
    }

    @Operation(description = "전체 Tease 조회")
    @GetMapping("/admin/all-tease")
    fun allTease(): Any {
        return teaseRepository.findAll()
    }

    @Operation(description = "특정 유저의 전체 Tease 조회")
    @GetMapping("/admin/user-all-tease")
    fun userAllTease(@RequestParam userId: Long): Any {
        return teaseRepository.findAllByUserId(userId)
    }

    @Operation(description = "특정 유저의 전체 Tease 삭제")
    @DeleteMapping("/admin/user-all-tease")
    fun deleteUserAllTease(@RequestParam userId: Long): ResultResponse {
        val allByUserId = teaseRepository.findAllByUserId(userId)
        teaseRepository.deleteAllById(allByUserId.map { it.id })
        return ResultResponse(true)
    }

    @Operation(description = "특정 유저와 해당 유저의 전체 npc들의 상태 조회")
    @GetMapping("/admin/user-npc-status")
    fun userAllNpcStatus(@RequestParam userId: Long): Any {
        val user = userRepository.findById(userId).orElseThrow()
        val npcList = userRepository.findAllByNpcAndUserIdForNpc(true, userId)
        return mapOf(
            "user" to user,
            "npcList" to npcList
        )
    }

//    @Operation(description = "테스트용 더미 데이터 생성")
//    @PostMapping("/admin/create-dummy-data")
//    fun createDummyData(): ResultResponse {
//        userController.createUser(0.0,)
//        userController.createUser()
//        userController.createUser()
//        return ResultResponse(true)
//    }
}
