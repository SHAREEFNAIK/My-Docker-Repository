package com.naik.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naik.bindings.PlanData;
import com.naik.service.IAdminServiceMgmt;

@RestController
@RequestMapping("/admin/api")
public class AdminServiceOperationController {

	@Autowired
    private	IAdminServiceMgmt adminService;
	
	@PostMapping("/register")
	public ResponseEntity<String> savePlan (@RequestBody PlanData plan){
		String msg = adminService.registerPlan(plan);
		return new ResponseEntity<String> (msg, HttpStatus.CREATED);
	}
	
	@GetMapping("/allplancategories")
	   public ResponseEntity<Map<Integer, String>> showAllPlanCategories (){
			Map<Integer,String> categorymap =adminService.getPlanCategories();
			return new ResponseEntity<Map<Integer,String>>(categorymap,HttpStatus.OK);
	}	
	
	@GetMapping("/allplans")
	public ResponseEntity<List<PlanData>> showAllTravelPlans(){
		List<PlanData> planslist= adminService.getAllPlans();
		return new ResponseEntity<List<PlanData>>(planslist,HttpStatus.OK);
	}
	
	@PostMapping("/showplan/{planId}")
		public ResponseEntity<PlanData> showTravelPlanById(@PathVariable Integer planId){
			PlanData plan= adminService.getPlanById(planId);
			return new ResponseEntity<PlanData>(plan,HttpStatus.OK);
	}
	
	@PutMapping("/update/{plan}")
	public ResponseEntity<String> updatePlan(@RequestBody PlanData plan){
			String msg= adminService.updatePlan(plan);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId){
		String msg= adminService.deletePlan(planId);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
		@PutMapping("/changestatus/{planId}/{status}")
		public ResponseEntity<String> changePlanStatus(@PathVariable Integer planId,@PathVariable String status){
			String msg= adminService.changePlanStatus(planId, status);
				return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
}
