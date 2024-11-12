package com.naik.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.naik.Exception.InvalidSSNException;

@RestControllerAdvice
public class AppRegistrationAdvice {
	@ExceptionHandler(InvalidSSNException.class)
	public ResponseEntity<String> handleInvalidSSN(InvalidSSNException exception){
		exception.printStackTrace();
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
	}

   @ExceptionHandler(Exception.class)
   public ResponseEntity<String>handleAllException(Exception exp){
	   return new ResponseEntity<String>(exp.getMessage(),HttpStatus.BAD_REQUEST);
   }
}
