package com.example.api.service

import com.example.api.client.SolumClient
import com.example.api.dto.CreateOrderRequest
import com.example.domain.entity.Order
import com.example.domain.entity.OrderMenu
import com.example.domain.repository.MenuRepository
import com.example.domain.repository.OrderMenuRepository
import com.example.domain.repository.OrderRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class OrderService(
    val orderRepository: OrderRepository,
    val orderMenuRepository: OrderMenuRepository,
    val menuRepository: MenuRepository,
    val solumClient: SolumClient,
) {
    @Transactional
    fun createOrders(request: CreateOrderRequest): Object? {
        // 메뉴 찾기
        val menus = menuRepository.findAllById(request.menus.map { it.id })

        // 주문 생성
        val order = orderRepository.save(
            Order(
                tableNumber = request.tableNumber,
                eslImage = request.eslImage,
                orderMenu = mutableListOf(),
            ),
        )

        // 주문메뉴를 생성한다
        val orderMenus = request.menus.map {
            OrderMenu(
                order = order,
                menuId = it.id,
                menuName = menus.find { menu -> menu.id == it.id }!!.menuName,
                menuCount = it.count,
            )
        }.toMutableList()

        // 주문메뉴를 저장한다.
        orderMenuRepository.saveAll(orderMenus)

        order.orderMenu = orderMenus

        // 주문된 영수증 이미지를 esl에 전송한다.
        return solumClient.pushLabelImage(labelCode = "085C1B40E1DD", elsImage = request.eslImage)
    }
}
