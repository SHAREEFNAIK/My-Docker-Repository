package com.naik.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naik.bindings.ChildrenInputs;
import com.naik.bindings.DcSummaryReport;
import com.naik.bindings.EducationInputs;
import com.naik.bindings.IncomeInputs;
import com.naik.bindings.PlanSelectionInputs;
import com.naik.entity.ApplicationRegistrationEntity;
import com.naik.entity.DcCaseEntity;
import com.naik.entity.DcChildrenEntity;
import com.naik.entity.DcEducationEntity;
import com.naik.entity.DcIncomeEntity;
import com.naik.entity.PlanEntity;
import com.naik.repository.IAppRegisterRepository;
import com.naik.repository.IDcCaseRepository;
import com.naik.repository.IDcChildrenRepository;
import com.naik.repository.IDcEducationRepository;
import com.naik.repository.IDcIncomeRepository;
import com.naik.repository.IPlanRepository;

@Service
public class DataCollectionMgmtServiceImpl implements IDataCollectionMgmtService {

	@Autowired
	private IAppRegisterRepository appRepo;
	@Autowired
	private IDcCaseRepository caseRepo;
	@Autowired
	private IPlanRepository planRepo;
	@Autowired 
	private IDcChildrenRepository childRepo;
	@Autowired
	private IDcEducationRepository educationRepo;
	@Autowired
	private IDcIncomeRepository incomeRepo;
	
	@Override
	public Integer generateCaseNumber(Integer appId) {
		Optional<ApplicationRegistrationEntity> appEntity =appRepo.findById(appId);
		DcCaseEntity caseEntity = new DcCaseEntity();
		caseEntity.setAppId(appId);
		return	caseRepo.save(caseEntity).getCaseNo();
	}

	@Override
	public List<String> showAllPlanNames() {
	   List<PlanEntity> listPlans = planRepo.findAll();
	   List<String> planNames = listPlans.stream().map(plan-> plan.getPlanName()).toList();
		return planNames;
	}

	@Override
	public Integer savePlanSelection(PlanSelectionInputs plan) {
		Optional<DcCaseEntity> caseEntity= caseRepo.findById(plan.getCaseNo());
		if(caseEntity.isPresent()) {
			DcCaseEntity entity =caseEntity.get();
			entity.setPlanId(plan.getPlanId());
			//save caseEntity
			caseRepo.save(entity);
			return entity.getCaseNo();
		}
		return 0;
	}

	@Override
	public Integer saveIncomeDetails(IncomeInputs inputs) {
		// convert binding object to entity object
		DcIncomeEntity incomeEntity = new DcIncomeEntity();
		BeanUtils.copyProperties(inputs, incomeEntity);
		incomeRepo.save(incomeEntity);
		return inputs.getCaseNo();
	}

	@Override
	public Integer saveEducationDetails(EducationInputs education) {
		// convert binding object to entity object
		DcEducationEntity eduEntity = new DcEducationEntity();
		BeanUtils.copyProperties(education, eduEntity);
		//use repo
		return  educationRepo.save(eduEntity).getCaseNo();
	}

	@Override
	public Integer saveChildrenDetails(List<ChildrenInputs> children) {
		// convert binding object to entity object
		children.forEach(child-> {
			DcChildrenEntity childEntity = new DcChildrenEntity();
			BeanUtils.copyProperties(child, childEntity);
			childRepo.save(childEntity);
		});
		return children.get(0).getCaseNo();
	}
	
	@Override
	public DcSummaryReport showReport(Integer caseNo) {
		
		// get multiple Entities based on caseNo
		List<DcChildrenEntity> listChildEntity =childRepo.findByCaseNo(caseNo);
		DcIncomeEntity incomeEntity =incomeRepo.findByCaseNo(caseNo);
		DcEducationEntity eduEntity = educationRepo.findByCaseNo(caseNo);
		Optional<ApplicationRegistrationEntity> optAppEntity = appRepo.findById(caseNo);
		Optional<DcCaseEntity> optCaseEntity = caseRepo.findById(caseNo);
		String planName= null;
		if(optCaseEntity.isPresent()) {
		    Integer planId=null;
			Integer appId=null;
			DcCaseEntity entity = optCaseEntity.get();
			planId=entity.getPlanId();
			appId=entity.getAppId();	
		Optional<PlanEntity> optEntity= planRepo.findById(planId);
		if (optEntity.isPresent())
			planName= optEntity.get().getPlanName();
		
		}
		ApplicationRegistrationEntity citizenEntity=null;
		// convert all Entity to bindings to send as report
		if(optAppEntity.isPresent())
			citizenEntity=optAppEntity.get();
		BeanUtils.copyProperties(optAppEntity,citizenEntity);
		
		EducationInputs eduInput= new EducationInputs();
		BeanUtils.copyProperties(eduEntity, eduInput);
		IncomeInputs inInputs= new IncomeInputs();
		BeanUtils.copyProperties(incomeEntity, inInputs);
		
		List<ChildrenInputs> childInputs = new ArrayList<ChildrenInputs>();
		listChildEntity.forEach(child->{
			ChildrenInputs inputs= new ChildrenInputs();
			BeanUtils.copyProperties(child, inputs);
			childInputs.add(inputs);
		});
		
		//prepare DcSummary Report Objects
		DcSummaryReport report = new DcSummaryReport();
		
		report.setChildDetails(childInputs);
		report.setCitizenDetails(citizenEntity);
		report.setEduDetails(eduInput);
		report.setIncomeDetails(inInputs);
		report.setPlanName(planName);
		return report;
	}

}
