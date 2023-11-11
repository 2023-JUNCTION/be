package com.example.domain.entity

import jakarta.persistence.*

@Entity
class Coordinate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coordinate_id", nullable = false)
    var id: Long = 0
    @Column(name = "latitude")
    var latitude: Double = 0.0
    @Column(name = "longitude")
    var longitude: Double = 0.0
}
