package com.example.domain.repository

import com.example.domain.entity.KineticHistory
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface KineticHistoryRepository : CrudRepository<KineticHistory, Long> {
    fun findAllByUserId(userId: Long): List<KineticHistory>
}
