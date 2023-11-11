package com.example.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "user_table")
class User: Character()
