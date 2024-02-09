package com.eat.eat_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EatServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EatServerApplication.class, args);
	}

}
