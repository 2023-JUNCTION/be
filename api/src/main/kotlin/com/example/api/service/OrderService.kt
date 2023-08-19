package com.example.api.service

import com.example.api.client.SolumClient
import com.example.api.dto.CreateOrderRequest
import com.example.api.dto.OrderMenuResponse
import com.example.api.dto.ReadOrderResponse
import com.example.api.dto.ReadOrdersResponse
import com.example.domain.entity.Order
import com.example.domain.repository.EslOrderRepository
// import com.example.domain.entity.OrderMenu
import com.example.domain.repository.MenuRepository
import com.example.domain.repository.OrderRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class OrderService(
    val orderRepository: OrderRepository,
//    val orderMenuRepository: OrderMenuRepository,
    val menuRepository: MenuRepository,
    val eslOrderRepository: EslOrderRepository,
    val solumClient: SolumClient,
) {
    @Transactional
    fun createOrders(request: CreateOrderRequest): Any? {
        // 메뉴 찾기
        val menus = menuRepository.findAllById(request.menus.map { it.id })

        // elsOrderNumber 찾기
        // 현재 주문 상태인 주문들 중에서 가장 작은 (가장 오른쪽) elsOrderNumber를 찾는다.
        // 만약 주문이 없다면 4로 초기화한다.
        val findNumber = orderRepository.findFirstByDoneFalseOrderByEslOrderNumberAsc()?.eslOrderNumber
        // findNumber가 없으면 4로 초기화하고  있으면 findNumber에서 1을 뺀다.
        val eslOrderNumber = findNumber?.minus(1) ?: 4

        // 주문 생성
        val order = orderRepository.save(
            Order(
                tableNumber = request.tableNumber,
                eslImage = request.eslImage,
//                orderMenu = mutableListOf(),
                eslOrderNumber = eslOrderNumber,
            ),
        )

        // 주문메뉴를 생성한다
//        val orderMenus = request.menus.map {
//            OrderMenu(
// //                order = order,
//                menuId = it.id,
//                menuName = menus.find { menu -> menu.id == it.id }!!.menuName,
//                menuCount = it.count,
//            )
//        }.toMutableList()

        // 주문메뉴를 저장한다.
//        orderMenuRepository.saveAll(orderMenus)

//        order.orderMenu = orderMenus

        // 주문된 영수증 이미지를 esl에 전송한다.
        val labelCode = eslOrderRepository.findByOrderNumber(eslOrderNumber.toLong())?.labelCode
            ?: throw Exception("eslOrder가 존재하지 않습니다.")

        return solumClient.pushLabelImage(labelCode = labelCode, elsImage = request.eslImage)
    }

    fun getOrders(): ReadOrdersResponse {
        val orders =  orderRepository.findAll()

        return ReadOrdersResponse(
            orders = orders.map {
                ReadOrderResponse(
                    id = it.id!!,
                    orderMenu = it.orderMenu.map { orderMenu ->
                        OrderMenuResponse(
                            id = orderMenu.id!!,
                            menuId = orderMenu.menuId,
                            menuName = orderMenu.menuName,
                            menuCount = orderMenu.menuCount,
                        )
                    },
                    done = it.done,
                )
            }
        )
    }
}
