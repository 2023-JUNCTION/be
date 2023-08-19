package com.example.domain.repository

import com.example.domain.entity.EslOrder
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EslOrderRepository : CrudRepository<EslOrder, Long> {
    fun findByOrderNumber(orderNumber: Long): EslOrder?
}
