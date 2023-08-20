package com.example.api.controller

import com.example.api.client.SolumClient
import com.example.api.dto.Color
import com.example.api.dto.CreateOrderRequest
import com.example.api.dto.EmptyResponse
import com.example.api.dto.ReadOrdersResponse
import com.example.api.service.CompleteOrderService
import com.example.api.service.OrderService
import com.example.common.utils.imageUrlToBase64
import com.example.domain.repository.EslOrderRepository
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
    val eslOrderRepository: EslOrderRepository,
    val solumClient: SolumClient,
) {
    @Operation(description = "주문 요청")
    @PostMapping
    fun createOrders(@RequestBody request: CreateOrderRequest): Any? {
        return orderService.createOrders(request)
    }

    @Operation(description = "주문 요청 v2 (imageUrl -> base64 자동 변환)")
    @PostMapping("/v2")
    fun createOrdersV2(@RequestBody request: CreateOrderRequest): Any? {
        request.eslImage = imageUrlToBase64(request.eslImage) ?: ""
        return orderService.createOrders(request)
    }

    @Operation(description = "주문 완료")
    @PostMapping("/complete/{eslOrderNumber}")
    fun completeOrder(@PathVariable eslOrderNumber: Int): EmptyResponse {
        completeOrderService.completeOrder(eslOrderNumber)
        return EmptyResponse
    }

    @Operation(description = "주문 전체 조회")
    @GetMapping
    fun getOrders(): ReadOrdersResponse {
        return orderService.getOrders()
    }

    @Operation(description = "완료되지 않은 주문 전체 조회")
    @GetMapping("/not-complete")
    fun getOrdersNotComplete(): ReadOrdersResponse {
        return orderService.getOrdersNotComplete()
    }

    @Operation(description = "esl led 푸시 by eslOrderNumber (지속 시간 10s)")
    @PostMapping("/esl-led/{eslOrderNumber}")
    fun pushEslLed(@PathVariable eslOrderNumber: Long, @RequestParam color: Color): Any? {
        val labelCode = eslOrderRepository.findByOrderNumber(eslOrderNumber)?.labelCode
            ?: throw IllegalStateException("주문 번호에 해당하는 esl order number가 없습니다.")

        return solumClient.pushLed(labelCode, color)
    }
}
