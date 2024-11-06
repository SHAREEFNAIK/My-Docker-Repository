package com.naik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naik.bindings.CoSummery;
import com.naik.service.ICorespondenceServiceMgmt;

@RestController
@RequestMapping("/coTriggerApi")
public class CoTriggerOperationsController {
	@Autowired
	private ICorespondenceServiceMgmt coService;
	@GetMapping("/procees-Trigger")
	public ResponseEntity<CoSummery>processAndUpdatePendingTrigger()throws Exception{
		CoSummery summery= coService.processPendingTriggers();
		return new ResponseEntity<CoSummery>(summery,HttpStatus.OK);
	}

}
