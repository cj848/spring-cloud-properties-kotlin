package com.example.springcloudpropertieskotlin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestPropertiesController(private val testProperties: TestProperties, private val testJavaProperties: TestJavaProperties) {

    @GetMapping("/test")
    fun test(): MutableMap<String?, String?> {
        val map = HashMap.newHashMap<String?, String?>(1)
        map.put("property1", testProperties.property1)
        map.put("property2", testProperties.property2)
        return map
    }

    @GetMapping("/test/java")
    fun javaTest(): MutableMap<String?, String?> {
        val map = HashMap.newHashMap<String?, String?>(1)
        map.put("property1", testJavaProperties.property1)
        map.put("property2", testJavaProperties.property2)
        return map
    }
}
