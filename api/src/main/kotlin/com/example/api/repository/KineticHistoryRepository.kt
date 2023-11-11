package com.example.api.repository

import com.example.domain.entity.KineticHistory
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface KineticHistoryRepository : CrudRepository<KineticHistory, Long>
