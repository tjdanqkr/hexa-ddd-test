package com.test.hexa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.test.hexa")
@EntityScan("com.test.hexa")
@EnableJpaRepositories("com.test.hexa")
public class HexaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HexaApplication.class, args);
	}

}
