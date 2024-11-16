package com.naik;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JrtpUnitTestingApplicationTests {
	
   //testing Adddition
	
	private static MathOperations math;
	@BeforeAll
	 public static void setUpOnce() {
		math = new MathOperations();
	}
	
	@Test
	public void addWithAllPositives() {
		int Actual=math.Addition(10, 20);
		int Expected=30;
		assertEquals(Expected, Actual);
	}
	
	@Test
	public void addWithAllNegatives() {
		int Actual=math.Addition(-10, -20);
		int Expected=-30;
		assertEquals(Expected, Actual);
	}
	
	@Test
	public void addWithMixed() {
		int Actual=math.Addition(-10, 20);
		int Expected=10;
		assertEquals(Expected, Actual);
	}
	@Test
	public void addWithZeros() {
		int Actual=math.Addition(0, 0);
		int Expected=0;
		assertEquals(Expected, Actual);
	}
	
	//testing multiplication method
	
	@Test
	public void mulWithAllpositives() {
		int Actual=math.Mul(10, 50);
		int Expected=500;
		assertEquals(Expected, Actual);
	}
	
	@Test
	public void mullWithAllNegatuives() {
		int Actual=math.Mul(-10, -6);
		int Expected=60;
		assertEquals(Expected, Actual);
	}
	
	@Test
	public void mullWithMix() {
		int Actual=math.Mul(-10,4);
		int Expected=-40;
		assertEquals(Expected, Actual);
	}
	
	@Test
	public void mullWithZeros() {
		int Actual=math.Mul(0,0);
		int Expected=0;
		assertEquals(Expected, Actual);
	}
	
	@AfterAll
	public static void tearDownOnce() {
		math=null;
	}
	
	
}//c
