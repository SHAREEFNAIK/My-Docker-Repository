package com.naik.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/birthday")
public class Hello {
	@GetMapping("/wish/{name}")
	public ResponseEntity<String> wishing (@PathVariable String name){
		return new ResponseEntity<String>("Hello,"+name+"Wishing You A very Happiest BirthDay",HttpStatus.OK);
	}

}
