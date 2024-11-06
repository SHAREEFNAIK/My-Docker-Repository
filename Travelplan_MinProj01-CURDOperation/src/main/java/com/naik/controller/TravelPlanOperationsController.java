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

import com.naik.entity.TravelPlan;
import com.naik.service.ITravelPlanMgmtService;

@RestController
@RequestMapping("/travelplan/api")
public class TravelPlanOperationsController {
	// inject service impl obj
	@Autowired
	private ITravelPlanMgmtService planservice;
	
	@PostMapping("/register")
	public ResponseEntity<String> savePlan (@RequestBody TravelPlan plan){
		String msg = planservice.registerTravelPlan(plan);
		return new ResponseEntity<String> (msg, HttpStatus.CREATED);
	}
	@GetMapping("/allcategories")
   public ResponseEntity<?> showTravelPlanCategories (){
		Map<Integer,String> categorymap = planservice.getTravelPlanCategories();
		return new ResponseEntity<Map<Integer,String>>(categorymap,HttpStatus.OK);
		}	
	@GetMapping("/alltravelplans")
	public ResponseEntity<?> showAllTravelPlans(){
		List<TravelPlan> planslist= planservice.getAllTravelPlans();
		return new ResponseEntity<List<TravelPlan>>(planslist,HttpStatus.OK);
		}
		@PostMapping("/showplan/{planId}")
		public ResponseEntity<?> showTravelPlanById(@PathVariable Integer planId){
				TravelPlan plan= planservice.getTravelPlanById(planId);
				return new ResponseEntity<TravelPlan>(plan,HttpStatus.OK);
		}
		@PutMapping("/update/{plan}")
		public ResponseEntity<?> updatePlan(@RequestBody TravelPlan plan){
				String msg= planservice.updateTravelPlan(plan);
			return new ResponseEntity<String>(msg,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		@DeleteMapping("/delete/{planId}")
		public ResponseEntity<?> deletePlan(@PathVariable Integer planId){
			String msg= planservice.deleteTravelPlan(planId);
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		}
			@PutMapping("/changestatus/{planId}/{status}")
			public ResponseEntity<?> changePlanStatus(@PathVariable Integer planId,@PathVariable String status){
				String msg= planservice.changeTravelPlanStatus(planId, status);
					return new ResponseEntity<String>(msg,HttpStatus.OK);
		}
}
