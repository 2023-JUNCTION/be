package com.example.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@ComponentScan(value = ["com.example.*"])
@EntityScan(basePackages = ["com.example.domain.entity"])
@ConfigurationPropertiesScan
@SpringBootApplication
@EnableJpaAuditing
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}
