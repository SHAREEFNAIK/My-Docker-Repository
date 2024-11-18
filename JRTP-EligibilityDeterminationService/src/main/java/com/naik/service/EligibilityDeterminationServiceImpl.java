package com.naik.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naik.bindings.EligibilityDetailsOutput;
import com.naik.entity.ApplicationRegistrationEntity;
import com.naik.entity.CoTriggerEntity;
import com.naik.entity.DcCaseEntity;
import com.naik.entity.DcChildrenEntity;
import com.naik.entity.DcEducationEntity;
import com.naik.entity.DcIncomeEntity;
import com.naik.entity.EligibilityDetailsEntity;
import com.naik.entity.PlanEntity;
import com.naik.repository.IAppRegisterRepository;
import com.naik.repository.ICoTriggerRepository;
import com.naik.repository.IDcCaseRepository;
import com.naik.repository.IDcChildrenRepository;
import com.naik.repository.IDcEducationRepository;
import com.naik.repository.IDcIncomeRepository;
import com.naik.repository.IEligibilityDetailsRepository;
import com.naik.repository.IPlanRepository;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
@Service
public class EligibilityDeterminationServiceImpl implements IEligibilityDeterminationServiceMgmt {

	
	@Autowired
	IDcCaseRepository caseRepo;
	@Autowired
	IPlanRepository planRepo;
	@Autowired
	IAppRegisterRepository appRegisterRepo;
	@Autowired
	IDcIncomeRepository incomeRepo;
	@Autowired
	IDcChildrenRepository childRepo;
	@Autowired
	IDcEducationRepository eduRepo;
	@Autowired
	ICoTriggerRepository triggerRepo;
	@Autowired
	IEligibilityDetailsRepository elgiRepo;
	
	
	@Override
	public EligibilityDetailsOutput determineEligibility(int caseNo) {
		
		Integer appId=null;
		Integer planId=null;
		Integer citizenAge=0;
		 String citizenName=null;
		 long citizenSSN=0L;
		
		// get above two values from DccaseEntity 
		Optional<DcCaseEntity> optCaseEntity= caseRepo.findById(caseNo);
		if (optCaseEntity.isPresent()) {
			DcCaseEntity caseEntity= new DcCaseEntity();
			appId=caseEntity.getAppId();
			planId= caseEntity.getPlanId();
		}
		
		// get Plan Name
		String planName=null;
		Optional<PlanEntity> optPlanEntity= planRepo.findById(planId);
		if(optPlanEntity.isPresent()) {
			PlanEntity entity= new PlanEntity();
			planName= entity.getPlanName();
		}
		
		// calculate citizen Age and get full name from appRegis entity
		Optional<ApplicationRegistrationEntity>optRegisEntity= appRegisterRepo.findById(appId);
		if(optRegisEntity.isPresent()) {
			ApplicationRegistrationEntity entity= optRegisEntity.get();
			
			 citizenName= entity.getFullName();
			 
			  citizenSSN=entity.getSsn();
			 // calculate age
			 
			LocalDate  holderAge=entity.getDob();
			LocalDate sysDate= LocalDate.now();
			citizenAge= Period.between(holderAge,sysDate).getYears();
		}
		
		//call helper method to check Eligibility Conditions
		EligibilityDetailsOutput elgiOutput= checkEligibility(caseNo,planName,citizenAge);
		// set citizen name
		elgiOutput.setHolderName(citizenName);
		// save Elgibility Entity object
		EligibilityDetailsEntity elgiEntity= new EligibilityDetailsEntity();
		BeanUtils.copyProperties(elgiOutput, elgiEntity);
			elgiEntity.setCaseNo(caseNo);
			elgiEntity.setHolderSSN(citizenSSN);
			elgiRepo.save(elgiEntity);
			
			// save CO Trigger object
			CoTriggerEntity triggerEntity= new CoTriggerEntity();
			triggerEntity.setCaseNo(caseNo);
			triggerEntity.setCoTriggerStatus("pending");
			triggerRepo.save(triggerEntity);
			
		return elgiOutput;
	}//method

	public EligibilityDetailsOutput checkEligibility(int caseNo, String planName,int citizenAge) {
		
		EligibilityDetailsOutput elgiOutput= new EligibilityDetailsOutput();
		
		Optional<DcIncomeEntity>optIncome= incomeRepo.findById(caseNo);
		Long personIncome=null;
		Long propertyIncome=null;
		if(optIncome.isPresent()) 
			personIncome=optIncome.get().getEmpIncome();
			propertyIncome=optIncome.get().getPropertyIncome();
			elgiOutput.setPlanName(planName);
			
		if(planName.equalsIgnoreCase("SNAP")) {
			if(personIncome<=300) {
				elgiOutput.setPlanStatus("Approved");
				elgiOutput.setBenefitAmount(500);
			}else {
				elgiOutput.setPlanStatus("Rejected");
				elgiOutput.setDenialReason("Higher Income");
			}
		}//SNAP
		if(planName.equalsIgnoreCase("MEDAID")) {
			if(personIncome<=300 & propertyIncome==0) {
				elgiOutput.setPlanStatus("Approved");
				elgiOutput.setBenefitAmount(800);
			}else {
				elgiOutput.setPlanStatus("Rejected");
				elgiOutput.setDenialReason("Has Property Income");
			}
		}//MEDAID
		if(planName.equalsIgnoreCase("MEDCARE")) {
			if(citizenAge>=65) {
				elgiOutput.setPlanStatus("Approved");
				elgiOutput.setBenefitAmount(1000);
			}else {
				elgiOutput.setPlanStatus("Rejected");
				elgiOutput.setDenialReason("Citizen Age Is Less Than 65");
			}
		}//MEDCARE
		if(planName.equalsIgnoreCase("CCAP")) {
			boolean kidsAgeCondition=true;
			boolean kidsCountCondition=false;
			List<DcChildrenEntity> childEntity= childRepo.findByCaseNo(caseNo);
			if(!childEntity.isEmpty())
				kidsCountCondition=true;			
			
			for(DcChildrenEntity child: childEntity) {
				LocalDate kidage= child.getChildDOB();
				int age =Period.between(kidage, LocalDate.now()).getYears();
				if(age>16) {
					kidsAgeCondition=false;
					break;
				}
			}//for
			
			if(personIncome<=300 & kidsAgeCondition & kidsCountCondition) {
				elgiOutput.setPlanStatus("Approved");
				elgiOutput.setBenefitAmount(400);
			}else {
				elgiOutput.setPlanStatus("Rejected");
				elgiOutput.setDenialReason("Plan Criteria Not Met ");
			}
		}//CCAP
		if(planName.equalsIgnoreCase("CAJW")) {
			Optional<DcEducationEntity>optEducationEntity = eduRepo.findById(caseNo);
			Integer passOutYear= optEducationEntity.get().getPassOutYear();
			if (personIncome==0 & passOutYear<LocalDate.now().getYear()) {
				elgiOutput.setPlanStatus("Approved");
				elgiOutput.setBenefitAmount(700);
			}else {
				elgiOutput.setPlanStatus("Rejected");
				elgiOutput.setDenialReason("Plan Conditions Not Satiesfied");
			}
			
		}//CAJW
		if(planName.equalsIgnoreCase("QHP")) {
			if(citizenAge>1) {
				elgiOutput.setPlanStatus("Approved");
			}else {
				elgiOutput.setPlanStatus("Rejected");
				elgiOutput.setDenialReason("Plan Conditions Not Satiesfied");
			}
		}
		//set values for common properties only if plan is approved
		if (elgiOutput.getPlanStatus().equalsIgnoreCase("Approved")) {
			
			elgiOutput.setStartDate(LocalDate.now());
			elgiOutput.setEndDate(LocalDate.now().plusYears(3));
		}
	
	return elgiOutput;	
	}
}//class
