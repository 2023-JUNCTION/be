package com.example.api.repository

import com.example.domain.entity.Tease
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TeaseRepository : CrudRepository<Tease, Long>
