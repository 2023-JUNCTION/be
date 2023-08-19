package com.example.api.service

import com.example.api.client.SolumClient
import com.example.domain.repository.EslOrderRepository
import com.example.domain.repository.OrderRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CompleteOrderService(
    val orderRepository: OrderRepository,
    val eslOrderRepository: EslOrderRepository,
    val solumClient: SolumClient,
) {
    @Transactional
    fun completeOrder(eslOrderNumber: Int) {
        val order = orderRepository.findFirstByDoneFalseAndEslOrderNumberOrderByCreateAtDesc(eslOrderNumber)
            ?: throw Exception("주문이 존재하지 않습니다.")

        order.complete()

        // eslOrderNumber는 4, 3, 2, 1, 0 번 순서
        // 완료된 주문 esl의 다음 esl의 이미지들을 모두 앞으로 한 칸씩 당겨야 한다
        val orders = orderRepository.findAllByDoneFalseAndEslOrderNumberLessThan(eslOrderNumber)

        // buttonEslOrderNumber 보다 작은 주문들의 eslOrderNumber를 앞으로 한 칸씩 당긴다 (+1) 4를 초과하지 않도록 한다.
        val images = orders.map { it.eslImage }
        orders.forEach {
            if (it.eslOrderNumber < 4) it.moveForward()
        }
        // 3, 2, 1, 0 번 순서

        // buttonEslOrderNumber 보다 작은 주문들의 eslLabelCode를 가지고 images에서 pushLabelImage 호출한다.
        val eslOrders = eslOrderRepository.findAllByOrderNumberLessThan(eslOrderNumber.toLong())

        for (i in eslOrderNumber downTo 1) {
            solumClient.pushLabelImage(eslOrders[eslOrderNumber].labelCode, images[eslOrderNumber - 1])
        }

        orderRepository.saveAll(orders)
    }
}
