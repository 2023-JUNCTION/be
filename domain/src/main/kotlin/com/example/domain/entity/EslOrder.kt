package com.example.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "esl_order")
@Entity
class EslOrder(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    var id: Long? = null,

    @Column(name = "orderName")
    var orderNumber: Long,

    @Column(name = "labelCode")
    var labelCode: String,
)
