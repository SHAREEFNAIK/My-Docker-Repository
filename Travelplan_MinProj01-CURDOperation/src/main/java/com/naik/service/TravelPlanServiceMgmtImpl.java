package com.naik.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naik.configs.AppConfigProps;
import com.naik.constants.TravelPlanConstants;
import com.naik.entity.PlanCategory;
import com.naik.entity.TravelPlan;
import com.naik.repo.ICategoryPlanRepository;
import com.naik.repo.ITravelPlanRepository;

@Service
public class TravelPlanServiceMgmtImpl implements ITravelPlanMgmtService {

	@Autowired
	private ITravelPlanRepository TravelPlanRepo;
	@Autowired
	private ICategoryPlanRepository CategoryPlanRepo;
	
	private Map<String, String> messege;
	@Autowired
	public TravelPlanServiceMgmtImpl(AppConfigProps props) {
		messege = props.getMesseges();
	}
	
	@Override
	public String registerTravelPlan(TravelPlan plan) {
		TravelPlan Tplan = TravelPlanRepo.save(plan);
		return Tplan.getPalnId()!=null?TravelPlanConstants.SAVE_SUCCESS+plan.getPalnId()
		:TravelPlanConstants.SAVE_FAILURE;
	}
	@Override
	public Map<Integer, String> getTravelPlanCategories() {
		List<PlanCategory> categoryList= CategoryPlanRepo.findAll();
		/* Create a map collection to collect list of category id and category name 
		from list of plancategory objects **/
		Map<Integer,String> categoryMap = new HashMap<Integer,String>();
		// use foreach() to add category id , category name to map collection
		 categoryList.forEach(category ->{ 
			 categoryMap.put(category.getCategoryId(), category.getCategoryName());
		 });
		// return map collection having required column
		return categoryMap;
	}
	@Override
	public List<TravelPlan> getAllTravelPlans() {
		List<TravelPlan> travelPlanList = TravelPlanRepo.findAll();
		return travelPlanList;
	}
	@Override
	public TravelPlan getTravelPlanById(Integer planid) {
		Optional<TravelPlan> opt = TravelPlanRepo.findById(planid);
		if(opt.isPresent()) {
			TravelPlan plan = opt.get();
			return plan;
		}
		// since return type is obj use new 
		else {
		throw new  IllegalArgumentException(TravelPlanConstants.FIND_BY_ID_FAILURE);
			 }	
 }
	@Override
	public String updateTravelPlan(TravelPlan plan) {
		Optional<TravelPlan> opt = TravelPlanRepo.findById(plan.getPalnId());
		if(opt.isPresent()) {
			// save the plan by using its repo
			TravelPlanRepo.save(plan);
			return TravelPlanConstants.UPDATE_SUCCESS+plan.getPalnId();
		}
		return TravelPlanConstants.UPDATE_FAILURE;
	}
	@Override
	public String deleteTravelPlan(Integer planid) {
		//use opt obj to receive plan 
		// use repo to get paln
		Optional<TravelPlan> opt =TravelPlanRepo.findById(planid);
		if(opt.isPresent()) {
			TravelPlanRepo.deleteById(planid);
			return TravelPlanConstants.DELETE_SUCCESS+planid;
		}
		    return TravelPlanConstants.DELETE_FAILURE;
	}
	@Override
	public String changeTravelPlanStatus(Integer planid, String status) {
		Optional<TravelPlan> opt = TravelPlanRepo.findById(planid);
		if(opt.isPresent()) {
			//convert opt to entity obj to save oprn
			TravelPlan plan=opt.get();
			// use setters
			plan.setActiveSw(status);
			//use repo to save
			TravelPlanRepo.save(plan);
			return TravelPlanConstants.STATUS_CHANGE_SUCCESS;
		}
		return TravelPlanConstants.STATUS_CHANGE_FAILURE;
	}
}
