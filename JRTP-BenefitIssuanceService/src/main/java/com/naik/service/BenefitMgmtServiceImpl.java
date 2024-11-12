package com.naik.service;

import java.sql.Date;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BenefitMgmtServiceImpl implements IBenefitIssuanceServiceMgmt {
	@Autowired
	private JobLauncher launcher;
	@Autowired
	private Job job;
	@Override
	public JobExecution sendBenefitAmount() throws Exception {
		// prepare job params
		JobParameter<Date> param= new JobParameter<Date>((Date) new java.util.Date(), Date.class);
		Map<String,JobParameter<?>> map=  Map.of("param1", param);
		JobParameters parameters= new JobParameters(map);
		//call run method
		JobExecution execution=launcher.run(job, parameters);
		System.out.println("executionStatus is:"+ execution.getExitStatus());
		return execution;
	}

}
