package ru.format.demo.licenseservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties(prefix = "example")
@Configuration
public class ServiceConfig {

    private String property;
}
