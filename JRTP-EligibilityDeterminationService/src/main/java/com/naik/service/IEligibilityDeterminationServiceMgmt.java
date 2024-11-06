package com.naik.service;

import com.naik.bindings.EligibilityDetailsOutput;

public interface IEligibilityDeterminationServiceMgmt {

	public EligibilityDetailsOutput determineEligibility(int caseNo);
	
}
