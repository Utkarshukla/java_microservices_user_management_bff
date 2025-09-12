package com.erp.users.mapper;

import com.erp.users.dto.UserDto;
import com.erp.users.entity.User;

public class UserMapper {
	public static User mapToUserEntity(UserDto userDto, User user) {
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		return user;
	}
	
	public static UserDto mapToUserDto(User user, UserDto userDto) {
		userDto.setId(String.valueOf(user.getId()));
		userDto.setUsername(user.getUsername());
		userDto.setEmail(user.getEmail());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		return userDto;
	}
}
