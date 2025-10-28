package com.project.immersionstudy.testContainer

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.MySQLContainer

class TestContainersInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

    companion object {
        private val mysql = MySQLContainer<Nothing>("mysql:8.4.5").apply {
            withDatabaseName("test_db")
            withUsername("test")
            withPassword("test")
            withInitScript("db/init.sql")
            start()
        }
    }

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        TestPropertyValues.of(
            "spring.datasource.url=${mysql.jdbcUrl}",
            "spring.datasource.username=${mysql.username}",
            "spring.datasource.password=${mysql.password}",
        ).applyTo(applicationContext.environment)
    }
}