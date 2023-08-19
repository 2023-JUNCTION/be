package com.example.api.service

import com.example.api.dto.CreateEslOrderRequest
import com.example.domain.entity.EslOrder
import com.example.domain.repository.EslOrderRepository
import org.springframework.stereotype.Service

@Service
class EslOrderService(
    val eslOrderRepository: EslOrderRepository,
) {
    fun createEslOrder(request: CreateEslOrderRequest): EslOrder {
        return eslOrderRepository.save(
            EslOrder(
                orderNumber = request.orderNumber,
                labelCode = request.labelCode,
            ),
        )
    }

    fun updateEslOrder(request: CreateEslOrderRequest, id: Long): EslOrder {
        return eslOrderRepository.save(
            EslOrder(
                id = id,
                orderNumber = request.orderNumber,
                labelCode = request.labelCode,
            ),
        )
    }

    fun getAllEslOrders(): Any? {
        return eslOrderRepository.findAll()
    }

    fun deleteEslOrder(id: Long) {
        eslOrderRepository.deleteById(id)
    }
}
