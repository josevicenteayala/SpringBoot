package com.spring.boot.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
		
		Integer integerA = 2;
		Integer integerB = 8;
		int comparator = Integer.compare(integerA, integerB);
		System.out.println("TestApplication.main() "+comparator);
	}

}
