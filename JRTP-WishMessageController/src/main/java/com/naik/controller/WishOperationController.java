package com.naik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naik.model.UserInfo;
import com.naik.service.WishMessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/wishAPI")
public class WishOperationController {
	
	@Autowired
	private WishMessageService wishService;
	
	@GetMapping("/wish")
  public ResponseEntity<String> sendWish(){
		String msg= wishService.showMessage();
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> addUser(@RequestBody UserInfo  info) {
		String msg= wishService.registerUser();
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	}
	
}
