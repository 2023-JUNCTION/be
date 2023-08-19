package com.example.domain.repository

import com.example.domain.entity.Menu
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuRepository : CrudRepository<Menu, Long>
