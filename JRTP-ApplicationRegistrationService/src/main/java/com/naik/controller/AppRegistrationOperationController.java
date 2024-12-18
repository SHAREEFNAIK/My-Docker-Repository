package com.naik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naik.Exception.InvalidSSNException;
import com.naik.bindings.AppRegistrationInputs;
import com.naik.service.IAppRegistrationService;

@RestController
@RequestMapping("/app-register")
public class AppRegistrationOperationController {
	
	@Autowired
	private IAppRegistrationService appService;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveCitizenApplication(AppRegistrationInputs inputs) throws Exception{
		
		
		Integer id= appService.registerCitizenApplication(inputs);
		return new ResponseEntity<String>("citizen application saved with id:"+id,HttpStatus.CREATED);
		 
	}

}
