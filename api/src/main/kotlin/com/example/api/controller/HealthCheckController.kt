package com.example.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["*"])
@Tag(name = "헬스체크 API", description = "Swagger 테스트입니다")
class HealthCheckController {

    @Operation(description = "헬스체크")
    @GetMapping("/healthCheck")
    fun healthCheck() = "OK"
}
