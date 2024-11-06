package com.naik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naik.bindings.EligibilityDetailsOutput;
import com.naik.service.IEligibilityDeterminationServiceMgmt;

@RestController
@RequestMapping("/eligibility-api")
public class EligibilityDeterminationOperationController {

	@Autowired
	IEligibilityDeterminationServiceMgmt elgiService;
	
	@PostMapping("/verifyEligibility/{caseNo}")
	public ResponseEntity<EligibilityDetailsOutput> citizenEligibilityDetermination(@PathVariable Integer caseNo){
		EligibilityDetailsOutput result= elgiService.determineEligibility(caseNo);
		return new ResponseEntity<EligibilityDetailsOutput>(result,HttpStatus.OK);
	}
	
}
