package com.example.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "menu")
@Entity
class Menu(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    var id: Long,

    @Column(name = "menuName")
    var menuName: String,

    @Column(name = "menuPrice")
    var menuPrice: Int,
)
