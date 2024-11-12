package com.naik.service;

import org.springframework.batch.core.JobExecution;

public interface IBenefitIssuanceServiceMgmt {

	public JobExecution sendBenefitAmount() throws Exception;
}
