package com.naik;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StringOperationsTest {
	
	private static  StringOperations polindrom;
	
	@BeforeAll
	public static void setUpOnce() {
		polindrom= new StringOperations();
	}
	
	// testing isPolindrome method
	@ParameterizedTest
	@ValueSource(strings= {"madam","nitin","malayalam","121"})
	public void isPolindromeTest(String str) {
		boolean actual=polindrom.isPolindrome(str);
		assertTrue(actual);
	}
	
	@AfterAll
	public static void tearDownOnce() {
		polindrom=null;
	}
	
}//class
