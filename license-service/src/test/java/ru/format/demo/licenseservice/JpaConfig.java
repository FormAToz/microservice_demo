package ru.format.demo.licenseservice;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import ru.format.demo.licenseservice.gw.output.dao.repository.LicenseRepository;

@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@TestConfiguration
public class JpaConfig {

    @MockBean
    private LicenseRepository licenseRepository;

    @Bean
    public LicenseRepository licenseRepository() {
        return licenseRepository;
    }
}
