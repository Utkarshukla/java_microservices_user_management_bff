package com.erp.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
    info = @Info(
        title = "ERP User Service API",
        version = "1.0.0",
        description = "API documentation for the ERP User Service. This service manages users, authentication, and permissions for the ERP system. It provides endpoints for user registration, login, profile management, and administrative operations.",
        termsOfService = "https://erp.example.com/terms",
        contact = @Contact(
            name = "Utkarsh Shukla",
            email = "utkarsh.shukla@example.com",
            url = "https://github.com/utkarshshukla"
        ),
        license = @io.swagger.v3.oas.annotations.info.License(
            name = "Apache 2.0",
            url = "http://www.apache.org/licenses/LICENSE-2.0.html"
        )
    )
)
public class ErpUserApplication {

	public static void main(String[] args) {
		System.out.println("User Service is starting...");
		SpringApplication.run(ErpUserApplication.class, args);
	}

}
