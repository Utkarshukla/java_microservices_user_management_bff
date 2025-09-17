package com.erp.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

	private String id;
	private String username;
	private String password;

	@NotEmpty(message = "Email is required")
	@Email(message = "Invalid email address")
	private String email;
	
	@NotEmpty(message = "First name is required")
	@Size(min = 3, max = 20, message = "First name must be between 3 and 20 characters")
	private String firstName;
	
	private String lastName;
	private Long businessId;
	private String userType;
}
