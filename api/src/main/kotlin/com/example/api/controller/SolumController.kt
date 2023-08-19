package com.example.api.controller

import com.example.api.client.SolumClient
import com.example.api.dto.CreateEslOrderRequest
import com.example.api.dto.EmptyResponse
import com.example.api.service.EslOrderService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "Solum API", description = "Solum 테스트용")
@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/api/solum")
class SolumController(
    val eslOrderService: EslOrderService,
    val solumClient: SolumClient,
) {
    @Operation(description = "solum 버전 확인")
    @GetMapping("/version")
    fun createOrders(): Any? {
        return solumClient.getVersion()
    }

    @Operation(description = "esl order 생성")
    @PostMapping("/esl-order")
    fun createEslOrder(@RequestBody request: CreateEslOrderRequest): Any? {
        return eslOrderService.createEslOrder(request)
    }

    @Operation(description = "esl order 수정")
    @PutMapping("/esl-order/{id}")
    fun updateEslOrder(@RequestBody request: CreateEslOrderRequest, @PathVariable id: Long): Any? {
        return eslOrderService.updateEslOrder(request, id)
    }

    @Operation(description = "esl order 전체 조회")
    @GetMapping("/esl-order")
    fun getAllEslOrders(): Any? {
        return eslOrderService.getAllEslOrders()
    }

    @Operation(description = "esl order 삭제")
    @DeleteMapping("/esl-order/{id}")
    fun deleteEslOrder(@PathVariable id: Long): EmptyResponse {
        eslOrderService.deleteEslOrder(id)
        return EmptyResponse
    }
}
