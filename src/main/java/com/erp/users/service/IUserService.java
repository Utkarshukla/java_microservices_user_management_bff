package com.erp.users.service;

import java.util.List;
import com.erp.users.dto.UserDto;

public interface IUserService {
	public void createUser(UserDto userDto);


	public UserDto showUser(Long id);


	public UserDto updateUser(UserDto userDto);


	public List<UserDto> getAllUsers();


	public void deleteUser(Long id);
}
