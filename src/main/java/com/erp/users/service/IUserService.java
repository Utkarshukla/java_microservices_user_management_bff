package com.erp.users.service;

import com.erp.users.dto.UserDto;

public interface IUserService {
	public void createUser(UserDto userDto);


	public void showUser(Long id);
}
