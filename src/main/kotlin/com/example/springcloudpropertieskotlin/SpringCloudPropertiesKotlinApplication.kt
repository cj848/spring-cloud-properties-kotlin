package com.example.springcloudpropertieskotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
@EnableConfigurationProperties(TestProperties::class, TestJavaProperties::class)
class SpringCloudPropertiesKotlinApplication

fun main(args: Array<String>) {
    runApplication<SpringCloudPropertiesKotlinApplication>(*args)
}
