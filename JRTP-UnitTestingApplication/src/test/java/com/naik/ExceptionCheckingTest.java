package com.naik;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExceptionCheckingTest {
	private static ExceptionChecking exp;
	
	@BeforeAll
	public static void setUpOnce() {
		exp= new ExceptionChecking();
	}
	
	@Test
	public void testExceptionCheckingWithNull() {
		
		assertThrows(NullPointerException.class,()->exp.ConvertStringIntoNumber(null));
	}
	
	@Test
	public void testExceptionCheckingWithEmtyString() {
		
		assertThrows(IllegalArgumentException.class,()->exp.ConvertStringIntoNumber(""));
	}
	@Test
	public void testExceptionCheckingWithStringWithLetters() {
		
		assertThrows(NumberFormatException.class,()->exp.ConvertStringIntoNumber("10y"));
	}
	@Test
	public void testExceptionCheckingWithString() {
		
		assertEquals(1235,exp.ConvertStringIntoNumber("1235"));
	}
	
	@AfterAll
	public static void tearDownOnce() {
		exp=null;
	}
	
}
