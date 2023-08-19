package com.example.api.controller

import com.example.api.client.SolumClient
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "Solum API", description = "Solum 테스트용")
@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/api/solum")
class SolumController(
    val solumClient: SolumClient,
) {
    @Operation(description = "solum 버전 확인")
    @GetMapping("/version")
    fun createOrders(): Any? {
        return solumClient.getVersion()
    }

    // alarm 상태 확인
}
