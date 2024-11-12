package com.naik.controller;

import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naik.service.IBenefitIssuanceServiceMgmt;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/bi-api")
public class benefitIssuanceController {
	@Autowired
	private IBenefitIssuanceServiceMgmt benefitService;
	
	@GetMapping("/send")
	public ResponseEntity<String> sendAmount() throws Exception{
		JobExecution execution = benefitService.sendBenefitAmount();
		return new ResponseEntity<String>(execution.getExitStatus().getExitDescription(),HttpStatus.OK);
	}
	

	
}
