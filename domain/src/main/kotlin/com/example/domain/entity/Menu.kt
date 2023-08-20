package com.example.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "menu")
@Entity
class Menu(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    var id: Long? = null,

    @Column(name = "menuName")
    var menuName: String,

    @Column(name = "menuPrice")
    var menuPrice: Int,

    @Column(name = "description")
    var description: String? = null,

    @Column(name = "imageUrl")
    var imageUrl: String? = null,

    @Column(name = "requiredTime")
    var requiredTime: Long // 소요시간을 초 단위로 저장
)
