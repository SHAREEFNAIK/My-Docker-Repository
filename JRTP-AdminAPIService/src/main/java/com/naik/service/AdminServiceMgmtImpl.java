package com.naik.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naik.bindings.PlanData;
import com.naik.configs.AppConfigProps;
import com.naik.constants.TravelPlanConstants;
import com.naik.entity.CategoryEntity;
import com.naik.entity.PlanEntity;
import com.naik.repositories.ICaseWorkerRepository;
import com.naik.repositories.ICategoryRepository;
import com.naik.repositories.IPlanRepository;


@Service
public class AdminServiceMgmtImpl implements IAdminServiceMgmt {

	@Autowired
	private IPlanRepository planRepo;
	@Autowired
	private ICategoryRepository CategoryRepo;
	@Autowired
	private ICaseWorkerRepository workerRepo;
	
	private Map<String,String> messege;
	@Autowired
	public AdminServiceMgmtImpl(AppConfigProps props) {
		messege = props.getMesseges();
	}
	
	@Override
	public String registerPlan(PlanData plan) {
		PlanEntity entity= new PlanEntity();
		BeanUtils.copyProperties(plan, entity);
		 PlanEntity savedEntity = planRepo.save(entity);
		return savedEntity.getPlanId()!=null?TravelPlanConstants.SAVE_SUCCESS+savedEntity.getPlanId()
		:TravelPlanConstants.SAVE_FAILURE;
	}
	@Override
	public Map<Integer, String> getPlanCategories() {
		List<CategoryEntity> categoryList= CategoryRepo.findAll();
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
	public List<PlanData> getAllPlans() {
		List<PlanEntity> listEntity = planRepo.findAll();
		List<PlanData> listPlanData= new ArrayList<PlanData>();
		listEntity.forEach(entity->{
			PlanData data= new PlanData();
			BeanUtils.copyProperties(listEntity, data);
			listPlanData.add(data);
		});
		return listPlanData;
	}
	@Override
	public PlanData getPlanById(Integer planid) {
		Optional<PlanEntity> optEntity = planRepo.findById(planid);
		if(optEntity.isPresent()) {
			PlanData data = new PlanData();
			BeanUtils.copyProperties(optEntity, data);
			return data;
		}
		// since return type is obj use new 
		else {
		throw new  IllegalArgumentException(TravelPlanConstants.FIND_BY_ID_FAILURE);
			 }	
 }
	@Override
	public String updatePlan(PlanData plan) {
		Optional<PlanEntity> optEntity= planRepo.findById(plan.getPlanId());
		if (optEntity.isPresent()) {
			PlanEntity entity = optEntity.get();
			BeanUtils.copyProperties(optEntity, entity);
			planRepo.save(entity);
			return TravelPlanConstants.UPDATE_SUCCESS+entity.getPlanId();	
		}
		return TravelPlanConstants.UPDATE_FAILURE;
	}
	@Override
	public String deletePlan(Integer planid) {
		Optional<PlanEntity> opt =planRepo.findById(planid);
		if(opt.isPresent()) {
			planRepo.deleteById(planid);
			return TravelPlanConstants.DELETE_SUCCESS+planid;
		}
		    return TravelPlanConstants.DELETE_FAILURE;
	}
	@Override
	public String changePlanStatus(Integer planid, String status) {
		Optional<PlanEntity> opt = planRepo.findById(planid);
		if(opt.isPresent()) {
			//convert opt to entity obj to save oprn
			PlanEntity plan=opt.get();
			// use setters
			plan.setActiveSw(status);
			//use repo to save
			planRepo.save(plan);
			return TravelPlanConstants.STATUS_CHANGE_SUCCESS;
		}
		return TravelPlanConstants.STATUS_CHANGE_FAILURE;
	}
}
