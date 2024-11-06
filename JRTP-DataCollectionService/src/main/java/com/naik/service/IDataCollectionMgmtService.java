package com.naik.service;

import java.util.List;

import com.naik.bindings.ChildrenInputs;
import com.naik.bindings.DcSummaryReport;
import com.naik.bindings.EducationInputs;
import com.naik.bindings.IncomeInputs;
import com.naik.bindings.PlanSelectionInputs;



public interface IDataCollectionMgmtService {

	
	public Integer generateCaseNumber(Integer appId);
	public List<String> showAllPlanNames();
	public Integer savePlanSelection(PlanSelectionInputs plan);
	public Integer saveIncomeDetails(IncomeInputs inputs);
	public Integer saveEducationDetails(EducationInputs education);
	public Integer saveChildrenDetails(List<ChildrenInputs> children);
	public  DcSummaryReport showReport(Integer caseNo);
	
	
}
