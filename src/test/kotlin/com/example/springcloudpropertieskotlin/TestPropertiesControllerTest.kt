package com.example.springcloudpropertieskotlin

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.ParameterizedTypeReference
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = ["spring.cloud.config.enabled=true"]
    )
class TestPropertiesControllerTest {
    @Autowired
    private lateinit var client: WebTestClient

    @Test
    fun testJava() {
        client.get()
            .uri("/test/java")
            .exchange()
            .expectStatus().isOk
            .expectBody(object : ParameterizedTypeReference<Map<String, String>>() {})
            .value {
                assertEquals(mapOf("property1" to "test5", "property2" to "test6"), it)
            }
    }

    private fun test(uri: String) {
        client.get()
            .uri(uri)
            .exchange()
            .expectStatus().isOk
            .expectBody(object : ParameterizedTypeReference<Map<String, String>>() {})
            .value {
                assertEquals(mapOf("property1" to null, "property2" to null), it)
            }

        client.post()
            .uri("/actuator/refresh")
            .exchange()
            .expectStatus().isOk

        client.get()
            .uri(uri)
            .exchange()
            .expectStatus().isOk
            .expectBody(object : ParameterizedTypeReference<Map<String, String>>() {})
            .value {
                assertEquals(mapOf("property1" to "test5", "property2" to "test6"), it)
            }
    }

    @Test
    fun `testKotlin fail if bug not found`() {
        client.get()
            .uri("/test")
            .exchange()
            .expectStatus().isOk
            .expectBody(object : ParameterizedTypeReference<Map<String, String>>() {})
            .value {
                assertEquals(mapOf("property1" to null, "property2" to null), it)
            }

        client.post()
            .uri("/actuator/refresh")
            .exchange()
            .expectStatus().isOk

        client.get()
            .uri("/test")
            .exchange()
            .expectStatus().isOk
            .expectBody(object : ParameterizedTypeReference<Map<String, String>>() {})
            .value {
                assertEquals(mapOf("property1" to null, "property2" to null), it)
            }
    }
}