package com.erp.users.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.erp.users.constant.UserConstant;
import com.erp.users.dto.UserDto;
import com.erp.users.entity.User;
import com.erp.users.exception.ResourceNotFoundException;
import com.erp.users.mapper.UserMapper;
import com.erp.users.repository.UserRepository;
import com.erp.users.service.IUserService;

@Service
@lombok.AllArgsConstructor
public class UserService implements IUserService {

	private UserRepository userRepository;
	
	
	@Override
	public List<UserDto> getAllUsers() {
		try {
			List<User> users = userRepository.findAll();
			List<UserDto> userDtos = users.stream().map(
					user -> UserMapper.mapToUserDto(user, new UserDto(null, null, null, null, null, null, null, null)))
					.toList();
			return userDtos;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
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
	public UserDto showUser(Long id) {
		try {
			User singleUser =  userRepository.findById((Long) id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
			UserDto userDto = UserMapper.mapToUserDto(singleUser, new UserDto(null, null, null, null, null, null, null, null));
			return userDto;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}
	
	@Override
	public UserDto updateUser(UserDto userDto) {
		try {
			
			User existingUser = userRepository.findById(Long.valueOf(userDto.getId())).orElseThrow(() -> new ResourceNotFoundException("User", "id", userDto.getId()));
			// convert UserDto to User entity
			User user = UserMapper.mapToUserEntity(userDto, existingUser);
			userRepository.save(user);
			UserDto updatedUserDto = UserMapper.mapToUserDto(user,
					new UserDto(null, null, null, null, null, null, null, null));
			return updatedUserDto;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void deleteUser(Long id) {
		try {
			User existingUser = userRepository.findById((Long) id)
					.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
			userRepository.delete(existingUser);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}



}
