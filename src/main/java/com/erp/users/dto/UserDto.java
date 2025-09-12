package com.erp.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

	private String id;
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private Long businessId;
	private String userType;
}
