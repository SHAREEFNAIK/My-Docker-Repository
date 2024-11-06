package com.naik.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TravelPlanAdvice {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllException( Exception e){
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException( IllegalArgumentException ill){
		return new ResponseEntity<String>(ill.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
