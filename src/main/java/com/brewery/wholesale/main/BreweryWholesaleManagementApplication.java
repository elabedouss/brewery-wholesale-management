package com.brewery.wholesale.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.brewery.wholesale.models")
@ComponentScan(basePackages = "com.brewery.wholesale")
@EnableJpaRepositories("com.brewery.wholesale.repositories")
@SpringBootApplication
public class BreweryWholesaleManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BreweryWholesaleManagementApplication.class, args);
	}

}
