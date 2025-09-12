package com.erp.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {

	private int statusCode;
	private String message;
	private Object data;
	private String error;
	private long timestamp;
}
