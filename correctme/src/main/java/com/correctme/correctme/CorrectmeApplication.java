package com.correctme.correctme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.correctme.correctme.model.repository.jpa")
@EnableMongoRepositories(basePackages = "com.correctme.correctme.model.repository.mongo")
public class CorrectmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CorrectmeApplication.class, args);
	}

}
