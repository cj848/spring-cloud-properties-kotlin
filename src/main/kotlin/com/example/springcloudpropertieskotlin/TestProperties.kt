package com.example.springcloudpropertieskotlin

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.cloud.context.environment.EnvironmentChangeEvent
import org.springframework.context.event.EventListener
import org.springframework.core.env.EnvironmentCapable

@ConfigurationProperties(prefix = "test")
@RefreshScope
open class TestProperties(
    var property1: String?,
    var property2: String?
) {

    @EventListener
    fun handleRefresh(event: EnvironmentChangeEvent) {
        println("Refreshed: " + event.getKeys())
        println("property1: " + property1)
        println("property1: " + property2)
        println(
            "environment property1: " + (event.getSource() as EnvironmentCapable).getEnvironment()
                .getProperty("test.property1")
        )
        println(
            "environment property2: " + (event.getSource() as EnvironmentCapable).getEnvironment()
                .getProperty("test.property2")
        )
    }
}
