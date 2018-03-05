package com.application.boot.contactos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ContactosBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactosBootApplication.class, args);
	}
}
