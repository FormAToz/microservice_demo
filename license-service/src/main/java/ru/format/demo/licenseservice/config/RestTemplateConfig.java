package ru.format.demo.licenseservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.format.demo.licenseservice.gw.input.rest.filter.UserContextInterceptor;

@Configuration
public class RestTemplateConfig {

    @LoadBalanced
    @Bean
    RestTemplate getRestTemplate() {
        RestTemplate template = new RestTemplate();
        template.getInterceptors().add(new UserContextInterceptor());
        return template;
    }
}
