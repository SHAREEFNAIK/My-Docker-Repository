package com.naik.service;

import java.util.List;
import java.util.Map;

import com.naik.bindings.PlanData;
import com.naik.entity.PlanEntity;

public interface IAdminServiceMgmt {
	
	//DECLARE THE CUSTOM METHODS
	
	public String registerPlan(PlanData plan);
	public Map<Integer,String> getPlanCategories();
	public List<PlanData>    getAllPlans();
	public PlanData getPlanById(Integer planid);
	public String updatePlan(PlanData plan);
	public String deletePlan(Integer planid);
	public String changePlanStatus(Integer planid,String status);
	
}
