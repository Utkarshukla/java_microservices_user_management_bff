package com.erp.users.service.imp;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.erp.users.constant.UserConstant;
import com.erp.users.dto.UserDto;
import com.erp.users.entity.User;
import com.erp.users.mapper.UserMapper;
import com.erp.users.repository.UserRepository;
import com.erp.users.service.IUserService;

@Service
@lombok.AllArgsConstructor
public class UserService implements IUserService {

	private UserRepository userRepository;
	@Override
	public void createUser(UserDto userDto) {
		try {
			
		
		// check if user exists by email or username
		Optional<User> existingUser = userRepository.findByEmailOrUsername(userDto.getEmail(), userDto.getUsername());
		
		if (existingUser.isPresent()) {
			if (existingUser.get().getEmail().equals(userDto.getEmail())) {
				throw new RuntimeException(UserConstant.EMAIL_ALREADY_EXISTS);
			} else {
				throw new RuntimeException(UserConstant.USERNAME_ALREADY_EXISTS);
			}
		}
		// convert UserDto to User entity
		User user = UserMapper.mapToUserEntity(userDto, new User());
		 userRepository.save(user);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public void showUser(Long id) {
		try {
			User singleUser =  userRepository.findById((Long) id).orElseThrow(() -> new RuntimeException(UserConstant.USER_NOT_FOUND));
			if (singleUser == null) {
				throw new RuntimeException(UserConstant.USER_NOT_FOUND);
			}
			
			UserDto userDto = UserMapper.mapToUserDto(singleUser, new UserDto(null, null, null, null, null, null, null, null));
			System.out.println(userDto);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}

}
