package ru.format.demo.licenseservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(JpaConfig.class)
@SpringBootTest
class LicenseServiceApplicationTests {

	@Test
	void contextLoads() {
	}
}
