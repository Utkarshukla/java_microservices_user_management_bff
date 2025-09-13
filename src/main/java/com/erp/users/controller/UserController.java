package com.erp.users.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.users.constant.UserConstant;
import com.erp.users.dto.ResponseDto;
import com.erp.users.dto.UserDto;
import com.erp.users.service.IUserService;
import java.util.List;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
	IUserService userService;
	
	@GetMapping("")
	public ResponseEntity<ResponseDto> indexUser() {
		try {
			
			// return all users 
			List<UserDto> users = userService.getAllUsers();
			
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseDto(200, UserConstant.LISTED, users, null, System.currentTimeMillis()));
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(500, UserConstant.INTERNAL_SERVER_ERROR, null, e.getMessage(), System.currentTimeMillis()));
		}
	}
	
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> storeUser(@RequestBody UserDto userDto) {
		try {
			userService.createUser(userDto);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(201,
					UserConstant.USER_CREATED_SUCCESSFULLY, userDto, null, System.currentTimeMillis()));
		} catch (RuntimeException re) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(400,
					UserConstant.INVALID_USER_DATA, null, re.getMessage(), System.currentTimeMillis()));
		
		} catch (Exception e) {

			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(500, UserConstant.FAILD_TO_CREATE_USER, null, e.getMessage(), System.currentTimeMillis()));
		} 
	}
	
	
	@GetMapping("/view")
	public ResponseEntity<ResponseDto> showUser(@RequestParam Long id) {
		try {
			UserDto userDto= userService.showUser(id);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(200, UserConstant.FETCHED, userDto, null, System.currentTimeMillis()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(500,
					UserConstant.INTERNAL_SERVER_ERROR, null, e.getMessage(), System.currentTimeMillis()));
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateUser(@RequestBody UserDto userDto) {
		try {
			UserDto existingUser = userService.updateUser(userDto);
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(200,
					UserConstant.USER_UPDATED_SUCCESSFULLY, existingUser, null, System.currentTimeMillis()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(500,
					UserConstant.FAILD_TO_UPDATE_USER, null, e.getMessage(), System.currentTimeMillis()));
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteUser(@RequestParam Long id) {
		try {
			// delete user by id
			 userService.deleteUser(id); // Check if user exists
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(200,
					UserConstant.USER_DELETED_SUCCESSFULLY, null, null, System.currentTimeMillis()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(500,
					UserConstant.FAILD_TO_DELETE_USER, null, e.getMessage(), System.currentTimeMillis()));
		}
	}
	
	
	

}
