package com.example.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "order_table")
@Entity
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    var id: Long? = null,

    @OneToMany(mappedBy = "order")
    var orderMenu: MutableList<OrderMenu> = mutableListOf(),

    @Column(name = "tableNumber")
    var tableNumber: Int,

    @Column(name = "eslImage", columnDefinition = "blob")
    var eslImage: String,

    @Column(name = "done")
    var done: Boolean = false,

    @Column(name = "type")
    var type: String? = null,

    @Column(name = "totalPrice")
    var totalPrice: Int? = 0,

    @Column(name = "eslOrderNumber")
    var eslOrderNumber: Int,
) : BaseEntity() {
    fun complete() {
        this.done = true
        completeAt = LocalDateTime.now()
    }

    fun moveForward() {
        eslOrderNumber += 1
    }
}
