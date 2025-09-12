package com.erp.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ErpUserApplication {

	public static void main(String[] args) {
		System.out.println("User Service is starting...");
		SpringApplication.run(ErpUserApplication.class, args);
	}

}
