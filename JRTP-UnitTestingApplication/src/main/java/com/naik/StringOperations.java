package com.naik;


public class StringOperations {

	public boolean isPolindrome(String str) {
		int length= str.length();
		StringBuilder reverse= new StringBuilder();
		for(int i=length-1;i>=0;i--) {
			reverse.append(str.charAt(i));
		}
		System.out.println(str+"........"+reverse);
		if(str.equals(reverse.toString())) 
			return true;
		else 
			return false;
		
	}
}
