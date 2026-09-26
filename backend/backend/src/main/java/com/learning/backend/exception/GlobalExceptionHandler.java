package com.learning.backend.exception;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.learning.backend.dto.ApiResponse;
import com.learning.backend.entity.ErrorLog;
import com.learning.backend.repository.ErrorLogRepository;


@RestControllerAdvice
public class GlobalExceptionHandler{
	
	@Autowired
	private ErrorLogRepository errorLogRepository;
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<Object>> HandleResourceNotFoundException(ResourceNotFoundException ex) {
		
		// 1. Audit / Save the error into the database
		ErrorLog errorLog = new ErrorLog(
				ex.getMessage(),
				ex.getClass().getSimpleName(),
				LocalDateTime.now()
				);
				
		errorLogRepository.save(errorLog);
		
		// 2. Return the uniform response to the client
		ApiResponse<Object> response = new ApiResponse<>(false,ex.getMessage(), null);
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
}
