package com.naik.Exception;

public class InvalidSSNException extends Exception{
	public InvalidSSNException() {
		super("Invalid SSN format. Please ensure the SSN follows the correct format (xxx-xx-xxxx).");
	}
	public InvalidSSNException(String msg) {
		super(msg);
	}

}
