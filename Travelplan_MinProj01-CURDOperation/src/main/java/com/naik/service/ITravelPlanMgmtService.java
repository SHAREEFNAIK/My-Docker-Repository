package com.naik.service;

import java.util.List;
import java.util.Map;

import com.naik.entity.TravelPlan;

public interface ITravelPlanMgmtService {
	
	//DECLARE THE CUSTOM METHODS
	
	public String registerTravelPlan(TravelPlan plan);
	public Map<Integer,String> getTravelPlanCategories();
	public List<TravelPlan>    getAllTravelPlans();
	public TravelPlan getTravelPlanById(Integer planid);
	public String updateTravelPlan(TravelPlan plan);
	public String deleteTravelPlan(Integer planid);
	public String changeTravelPlanStatus(Integer planid,String status);
	
}
