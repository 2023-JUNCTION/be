package com.example.api.service

import com.example.api.client.SolumClient
import com.example.api.dto.CompleteOrderRequest
import com.example.domain.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class CompleteOrderService(
    val orderRepository: OrderRepository,
    val solumClient: SolumClient,
) {
    // todo
    // 5초마다 스케줄링 돌려서..(??)
    // 우리의 esl을 전부 labels alarm 조회 api 호출
    // status가 true라면.. completeOrder 호출

    fun completeOrder(request: CompleteOrderRequest) {
        val order = orderRepository.findById(request.orderId).get()

        // order 상태를 완료로 변경한다. 시간을 변경한다.
        order.complete()

        // 완료버튼을 눌렀을때 그 esl의 라벨코드가 있을거고 -> 그 esl의 순서를 알아내고 -> 그 다음 순서부터 쭉 땡김

        // eslInfo를 통해 완료된 주문의 esl 번호를 알아낸다.
        // 완료된 주문의 esl의 다음 esl의 이미지들을 모두! 앞으로 한 칸씩 당겨야 한다.
        // esl 이미지를 쏘는 것은 solum client가 한다.
        println("order = $order")
    }

    // todo
    // 원복
    // 맨앞으로 간다 ?
    // 1번 esl의 labelCode = "085C1B40E1DD"
    // 2번 esl의 labelCode = ".."
    // 3번 esl의 labelCode = ".."

    // esl 개수 5개 . 이 이상 들어오면
}
