package com.example.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@ComponentScan(value = ["com.example.*"])
@EntityScan(basePackages = ["com.example.domain.entity"])
@ConfigurationPropertiesScan
@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = ["com.example.domain.repository"])
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}
