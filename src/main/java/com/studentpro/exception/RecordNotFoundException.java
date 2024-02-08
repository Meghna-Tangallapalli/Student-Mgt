package com.studentpro.exception;

public class RecordNotFoundException extends RuntimeException{

	private String message;

	public RecordNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	
	
}
