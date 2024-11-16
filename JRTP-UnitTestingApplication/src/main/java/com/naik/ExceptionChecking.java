package com.naik;

public class ExceptionChecking {
	public Integer ConvertStringIntoNumber(String str) {
		if(str.equals("")||str.length()==0||str==null) {
			throw new IllegalArgumentException(" String can't be null,empty,length zero");
		}
		return Integer.parseInt(str);
	}

}
