package com.erp.users.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import com.erp.users.dto.UserEnvDto;
import com.erp.users.service.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/users")
@Validated
@Tag(name = "User Controller", description = "User Management CRUD API At User Module")
@RefreshScope
@RequiredArgsConstructor
public class UserController  {
	private final IUserService userService;
	private final UserEnvDto userEnvDto;

	@Value("${build.version:unknown}")
	private String buildVersion;

	@Value("${build.author:unknown}")
	private String buildAuthor;

	@Value("${build.email:unknown}")
	private String buildEmail;

	@Value("${users.default.username:unknown}")
	private String defaultUsername;
	
	@GetMapping("")
	@Operation(summary = "Get all users", description = "Get all users from the database")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Users fetched successfully"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
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
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "User created successfully"),
		@ApiResponse(responseCode = "400", description = "Invalid user data"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@Operation(summary = "Create a new user", description = "Create a new user in the database")
	public ResponseEntity<ResponseDto> storeUser(@Valid @RequestBody UserDto userDto) {
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
	@Operation(summary = "Get a user by id", description = "Get a user by id from the database")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "User fetched successfully"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
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
	@Operation(summary = "Update a user", description = "Update a user in the database")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "User updated successfully"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<ResponseDto> updateUser(@Valid @RequestBody UserDto userDto) {
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
	@Operation(summary = "Delete a user", description = "Delete a user from the database")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "User deleted successfully"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<ResponseDto> deleteUser(@RequestParam
			@Pattern(regexp = "^[0-9]+$", message = "Invalid user ID")
			Long id) {
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
	
	
	@GetMapping("/config")
	@Operation(summary = "Get microservice info", description = "Get microservice info from application properties")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Microservice info fetched successfully"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<ResponseDto> msInfo(){
		try {
			// combine application env variables with config server properties
			String info = String.format(
				"%s by %s | version=%s | author=%s | email=%s | defaultUser=%s",
				userEnvDto.appName(), userEnvDto.author(), buildVersion, buildAuthor, buildEmail, defaultUsername);
			return new ResponseEntity<>(
					new ResponseDto(200, "User Service is up and running", info, null, System.currentTimeMillis()),
					HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(
					new ResponseDto(500, UserConstant.INTERNAL_SERVER_ERROR, null, e.getMessage(), System.currentTimeMillis()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
