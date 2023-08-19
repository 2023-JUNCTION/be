package com.example.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Table(name = "order_table")
@Entity
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    var id: Long,

    @OneToMany(mappedBy = "order")
    var orderMenu: MutableList<OrderMenu>,

    @Column(name = "tableNumber")
    var tableNumber: Int,

    @Column(name = "done")
    var done: Boolean,

    @Column(name = "type")
    var type: String,

    @Column(name = "totalPrice")
    var totalPrice: Int,
) : BaseEntity()
