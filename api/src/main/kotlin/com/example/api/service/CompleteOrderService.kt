package com.example.api.service

import com.example.api.client.SolumClient
import com.example.domain.repository.EslOrderRepository
import com.example.domain.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class CompleteOrderService(
    val orderRepository: OrderRepository,
    val eslOrderRepository: EslOrderRepository,
    val solumClient: SolumClient,
) {
    fun completeOrder(buttonEslOrderNumber: Int) {
        val order = orderRepository.findByEslOrderNumber(buttonEslOrderNumber)
            ?: throw Exception("주문이 존재하지 않습니다.")

        order.complete()

        // 완료된 주문 esl의 다음 esl의 이미지들을 모두 앞으로 한 칸씩 당겨야 한다
        val orders = orderRepository.findAllByEslOrderNumberGreaterThan(buttonEslOrderNumber)

        // buttonEslOrderNumber 보다 큰 .. esl 4, 3, 2, 1, 0 번의 이미지 긁어오기
        val images = orders.map { it.eslImage }

        // buttonEslOrderNumber 보다 큰 주문들의 eslOrderNumber를 앞으로 한 칸씩 당긴다
        orders.forEach { it.moveForward() }

        // buttonEslOrderNumber 보다 큰 주문들의 eslLabelCode를 가지고 images에서 pushLabelImage 호출한다.
        // images 인덱스 순서대로 eslLabelCode를 가지고 pushLabelImage 호출한다.
        val eslOrders = eslOrderRepository.findAllByOrderNumberGreaterThan(buttonEslOrderNumber.toLong())
        eslOrders.forEachIndexed { index, eslOrder ->
            solumClient.pushLabelImage(eslOrder.labelCode, images[index])
        }
    }
}
