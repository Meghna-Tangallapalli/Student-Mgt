package com.studentpro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.studentpro.errors.ErrorResponse;

@ControllerAdvice

public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<ErrorResponse> recordNotFoundException(RecordNotFoundException recordNotFound) {
		ErrorResponse error = new ErrorResponse();
		error.setMessage(recordNotFound.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ErrorResponse> apiException(ApiException apiException) {
		ErrorResponse error = new ErrorResponse();
		error.setMessage(apiException.getMessage());
		error.setStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);

	}
}
