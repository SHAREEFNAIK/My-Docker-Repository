package com.naik.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ssn-api")
public class SsnOperationController {

	@GetMapping("/find/{ssn}")
	public ResponseEntity<String> getStateName(@PathVariable Integer ssn){
		
		if(String.valueOf(ssn).length()!=9) {
			return new ResponseEntity<String>("invalid ssn no",HttpStatus.BAD_REQUEST);
			
		}
		
		String stateName = null;
		int stateCode = ssn%100;
		
		if(stateCode==01)			
			stateName= "Texas";
		
			else if(stateCode==02)				
				stateName= "Ohio";
		
				else if(stateCode==03)
					stateName= "NewYork";
					
					else if(stateCode==04)
						stateName= "California";
					else 
						stateName= "INVALID SSN";
				return new ResponseEntity<String>(stateName,HttpStatus.OK);
	}
	
	
}	

