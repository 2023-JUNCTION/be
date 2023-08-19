package com.example.api.controller

import com.example.api.dto.CompleteOrderRequest
import com.example.api.dto.CreateOrderRequest
import com.example.api.dto.EmptyResponse
import com.example.api.service.CompleteOrderService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "주문 API", description = "수리수리 마수리")
@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/api/orders")
class OrderController(
    val completeOrderService: CompleteOrderService,
) {
    @Operation(description = "주문 요청")
    @PostMapping
    fun createOrders(@RequestBody request: CreateOrderRequest): EmptyResponse {
        println(request)
        return EmptyResponse
    }

    @Operation(description = "주문 완료")
    @PostMapping("/complete")
    fun completeOrder(@RequestBody request: CompleteOrderRequest): EmptyResponse {
        completeOrderService.completeOrder()
        return EmptyResponse
    }
}
