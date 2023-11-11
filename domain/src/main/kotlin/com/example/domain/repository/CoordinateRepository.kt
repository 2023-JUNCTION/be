package com.example.domain.repository

import com.example.domain.entity.Coordinate
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CoordinateRepository : CrudRepository<Coordinate, Long>
