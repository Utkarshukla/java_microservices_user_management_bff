package com.erp.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.erp.users.dto.ResponseDto;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseDto> handleAllExceptions(Exception ex, WebRequest request) {
		ResponseDto response = new ResponseDto(500, "Internal Server Error", null, ex.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex,
			WebRequest request) {
		ResponseDto response = new ResponseDto(404, "Resource Not Found", null, ex.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}
