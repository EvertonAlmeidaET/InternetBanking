package com.api.Internetbanking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class InternetBankingExceptionsHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity handleException(Exception e) {
		return new ResponseEntity(e.getMessage(), HttpStatus.BAD_GATEWAY);
		
	}

}
