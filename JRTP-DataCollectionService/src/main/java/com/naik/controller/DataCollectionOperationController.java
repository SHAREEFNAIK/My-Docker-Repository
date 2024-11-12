package com.naik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naik.bindings.ChildrenInputs;
import com.naik.bindings.DcSummaryReport;
import com.naik.bindings.EducationInputs;
import com.naik.bindings.IncomeInputs;
import com.naik.bindings.PlanSelectionInputs;
import com.naik.service.IDataCollectionMgmtService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/dc-api")
public class DataCollectionOperationController {

	@Autowired
	IDataCollectionMgmtService dcService;
	
	@GetMapping("/planNames")
	public ResponseEntity<List<String>> getAllPlanNames(){
		List<String> planNames=dcService.showAllPlanNames();
		return new ResponseEntity<List<String>>(planNames,HttpStatus.OK);
	}
	
	@PostMapping("/generateCaseNo/{appId}")
	public ResponseEntity<Integer> generateCaseNumber(@PathVariable Integer appId){
		Integer caseNumber = dcService.generateCaseNumber(appId);
		return new ResponseEntity<Integer>(caseNumber,HttpStatus.CREATED);
	}
	
	@PutMapping("/savePlan")
	public ResponseEntity<Integer> saveChosenPlan(@RequestBody PlanSelectionInputs plan){
		Integer result= dcService.savePlanSelection(plan);
		return new ResponseEntity<Integer>(result,HttpStatus.CREATED);
	}
	
	@PostMapping("/saveIncome")
	public ResponseEntity<Integer>saveIncome(@RequestBody IncomeInputs inputs){
		Integer result= dcService.saveIncomeDetails(inputs);
		return new ResponseEntity<Integer>(result,HttpStatus.CREATED);
	}
	
	@PostMapping("/educationDetails")
	public ResponseEntity<Integer>saveEducationData(@RequestBody EducationInputs inputs){
		Integer result= dcService.saveEducationDetails(inputs);
		return new ResponseEntity<Integer>(result,HttpStatus.CREATED);
	}
	
	@PostMapping("/saveChilds")
	public ResponseEntity<Integer> saveChilds(@RequestBody List<ChildrenInputs> inputs){
		Integer result = dcService.saveChildrenDetails(inputs);
		return new ResponseEntity<Integer>(result,HttpStatus.CREATED);
	}
	
	@PostMapping("/generateReport/{caseNo}")
	public ResponseEntity<DcSummaryReport> generateReport(@PathVariable Integer CaseNo){
		DcSummaryReport report= dcService.showReport(CaseNo);
		return new ResponseEntity<DcSummaryReport>(report,HttpStatus.OK);
	}
}
