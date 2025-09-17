package com.erp.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import com.erp.users.dto.ResponseDto;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		Map<String, String> errors = new HashMap<>();
		List<ObjectError> validateErrorLists = ex.getBindingResult().getAllErrors();
		
		validateErrorLists.forEach((error) -> {
			String fieldName = ((org.springframework.validation.FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
//		errors.put("status", false);s
		return new ResponseEntity<>( errors, HttpStatus.BAD_REQUEST);
	}
	
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
