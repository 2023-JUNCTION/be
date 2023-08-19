package com.example.domain.repository

import com.example.domain.entity.Order
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : CrudRepository<Order, Long> {
    fun findFirstByDoneFalseAndEslOrderNumberOrderByCreateAtDesc(orderNumber: Int): Order?
    fun findFirstByDoneFalseOrderByEslOrderNumberAsc(): Order?
    fun findAllByDoneFalseAndEslOrderNumberLessThanEqual(orderNumber: Int): List<Order>
    fun findAllByDoneFalse(): List<Order>
}
