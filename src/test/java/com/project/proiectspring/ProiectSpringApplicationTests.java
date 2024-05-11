package com.project.proiectspring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan(basePackages = {"com.project.proiectspring.ControllersUnitTests", "com.project.proiectspring.ServicesUnitTests"})
class ProiectSpringApplicationTests {

	@Test
	void contextLoads() {



	}

}
