package com.erp.users.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.users.constant.UserConstant;
import com.erp.users.dto.ResponseDto;
import com.erp.users.dto.UserDto;
import com.erp.users.service.IUserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
	IUserService userService;
	
	@GetMapping("")
	public ResponseEntity<ResponseDto> indexUser() {
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseDto(200, UserConstant.LISTED, null, null, System.currentTimeMillis()));
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
	
	
	@GetMapping("/view/{id}")
	public ResponseEntity<ResponseDto> showUser(@RequestBody Long id) {
		try {
			userService.showUser(null);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(200, UserConstant.FETCHED, null, null, System.currentTimeMillis()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(500,
					UserConstant.INTERNAL_SERVER_ERROR, null, e.getMessage(), System.currentTimeMillis()));
		}
	}
	
	

}
