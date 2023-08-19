package com.example.api.service

import com.example.api.client.SolumClient
import com.example.api.dto.CreateOrderRequest
import org.springframework.stereotype.Service

@Service
class OrderService(
    val solumClient: SolumClient,
) {
    fun createOrders(request: CreateOrderRequest): Object? {
        return solumClient.pushLabelImage(labelCode = "085C1B40E1DD", elsImage = request.eslImage)
    }
}
