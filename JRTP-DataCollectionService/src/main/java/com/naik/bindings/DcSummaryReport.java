package com.naik.bindings;

import java.util.List;

import com.naik.entity.ApplicationRegistrationEntity;

import lombok.Data;
@Data
public class DcSummaryReport {

	private String planName; 
	private ApplicationRegistrationEntity citizenDetails;
	private IncomeInputs incomeDetails;
	private EducationInputs eduDetails;
	private List<ChildrenInputs> childDetails;
	
}
