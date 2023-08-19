package com.example.api.controller

import com.example.api.dto.CreateOrderRequest
import com.example.api.dto.EmptyResponse
import com.example.api.service.CompleteOrderService
import com.example.api.service.OrderService
import com.example.domain.entity.Order
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "주문 API", description = "수리수리 마수리")
@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/api/orders")
class OrderController(
    val orderService: OrderService,
    val completeOrderService: CompleteOrderService,
) {
    @Operation(description = "주문 요청")
    @PostMapping
    fun createOrders(@RequestBody request: CreateOrderRequest): Any? {
        return orderService.createOrders(request)
    }

    @Operation(description = "주문 완료")
    @PostMapping("/complete/{buttonEslOrderNumber}")
    fun completeOrder(@PathVariable buttonEslOrderNumber: Int): EmptyResponse {
        completeOrderService.completeOrder(buttonEslOrderNumber)
        return EmptyResponse
    }

    @Operation(description = "주문 전체 조회")
    @GetMapping
    fun getOrders(): Iterable<Order> {
        return orderService.getOrders()
    }
}
