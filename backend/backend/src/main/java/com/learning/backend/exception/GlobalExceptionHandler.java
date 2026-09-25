package com.learning.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.learning.backend.dto.ApiResponse;

public class GlobalExceptionHandler extends RuntimeException{
	
	public ResponseEntity<ApiResponse<Object>> HandleResourceNotFoundException(ResourceNotFoundException ex) {
		ApiResponse<Object> response = new ApiResponse<>(false,ex.getMessage(), null);
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
}
