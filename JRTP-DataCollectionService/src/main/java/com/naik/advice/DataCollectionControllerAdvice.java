package com.naik.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DataCollectionControllerAdvice {
	// Single Method To Handle All Exception
	
	@ExceptionHandler( Exception.class )
	public ResponseEntity<String> handleAllExceptions(Exception Exp){
		
		return new ResponseEntity<String>(Exp.getMessage(),HttpStatus.BAD_REQUEST);
	}

}
