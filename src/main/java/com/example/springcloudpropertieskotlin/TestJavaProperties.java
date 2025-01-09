package com.example.springcloudpropertieskotlin;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.EnvironmentCapable;

@ConfigurationProperties(prefix = "test")
@RefreshScope
public class TestJavaProperties {
    private String property1;
    private String property2;

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public String getProperty2() {
        return property2;
    }

    public void setProperty2(String property2) {
        this.property2 = property2;
    }

    @EventListener
    public void handleRefresh(EnvironmentChangeEvent event) {
        System.out.println("java Refreshed: " + event.getKeys());
        System.out.println("java property1: " + property1);
        System.out.println("java property1: " + property2);
        System.out.println("java environment property1: " + ((EnvironmentCapable)event.getSource()).getEnvironment().getProperty("test.property1"));
        System.out.println("java environment property2: " + ((EnvironmentCapable)event.getSource()).getEnvironment().getProperty("test.property2"));
    }
}
