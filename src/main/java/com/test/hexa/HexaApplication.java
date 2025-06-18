package com.test.hexa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.test")
@EntityScan("com.test")
@EnableJpaRepositories("com.test")
public class HexaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HexaApplication.class, args);
	}

}
