package com.erp.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(
	name = "Employee ",
	description = "Employee Management DTO",
	example = "UserDto.builder().id(1).username('test').password('test').email('test@test.com').firstName('test').lastName('test').businessId(1).userType('test').build()"
)
@AllArgsConstructor
public class UserDto {

	@Schema(
		name = "id",
		description = "Id of the user",
		example = "1"
	)
	private String id;
	@Schema(
		name = "username",
		description = "Username of the user",
		example = "test"
	)
	private String username;
	
	@Schema(
		name = "password",
		description = "Password of the user",
		example = "test"
	)
	private String password;

	@NotEmpty(message = "Email is required")
	@Email(message = "Invalid email address")
	@Schema(
		name = "email",
		description = "Email of the user",
		example = "test@test.com"
	)
	private String email;
	
	@NotEmpty(message = "First name is required")
	@Size(min = 3, max = 20, message = "First name must be between 3 and 20 characters")
	@Schema(
		name = "firstName",
		description = "First name of the user",
		example = "test"
	)
	private String firstName;

	@Schema(
		name = "lastName",
		description = "Last name of the user",
		example = "test"
	)
	private String lastName;
	@Schema(
		name = "businessId",
		description = "Business id of the user",
		example = "1"
	)
	private Long businessId;
	
	@Schema(
		name = "userType",
		description = "User type of the user",
		example = "test"
	)
	private String userType;
}
