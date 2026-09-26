package com.learning.backend.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class ErrorLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String message;
	private String errorType;
	private LocalDateTime timeStap;
	
	public ErrorLog(String message,String errorType,LocalDateTime timeStap) {
		this.message=message;
		this.errorType=errorType;
		this.timeStap=timeStap;
	}
	
}
