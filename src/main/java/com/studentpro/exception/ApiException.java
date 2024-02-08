package com.studentpro.exception;

public class ApiException extends RuntimeException {

	private String message;

	public ApiException(String message) {
		super();
		this.message = message;
	}
	
	
}
