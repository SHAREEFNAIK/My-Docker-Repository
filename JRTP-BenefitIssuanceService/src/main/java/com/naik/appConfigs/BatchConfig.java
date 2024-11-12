package com.naik.appConfigs;

import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import com.naik.bindings.EligibilityDetails;
import com.naik.entity.EligibilityDetailsEntity;
import com.naik.processor.EligibilityDeterminationProcessor;
import com.naik.repository.IEligibilityDetailsRepository;

@Configuration
public class BatchConfig {
	@Autowired
	private IEligibilityDetailsRepository elgiRepo;
	@Autowired
	private EligibilityDeterminationProcessor processor;
	@Bean(name="reader")
	public RepositoryItemReader<EligibilityDetailsEntity> createReader(){
		
		return new RepositoryItemReaderBuilder<EligibilityDetailsEntity>()
				.name("repo-reader")
				.repository(elgiRepo)
				.methodName("findAll")
				.sorts(Map.of("caseNo",Sort.Direction.ASC))
				.build();
			}// Reader
	@Bean(name="writer")
	public FlatFileItemWriter<EligibilityDetails> createWriter(){
		return new FlatFileItemWriterBuilder<EligibilityDetails>()
				.name("Item-Writer")
				.resource( new FileSystemResource("Beneficiary-List.csv"))
				.lineSeparator("\r\n")
				.delimited().delimiter(",")
				.names("caeNo","holderSSN","holderName","planName","planStatus","benefitAmount","bankNumber","bankName")
				.build();
	}// writer
	@Bean(name="step1")
	public Step createStep(JobRepository jobRepo,PlatformTransactionManager transMgmr) {
		return new StepBuilder("step1",jobRepo)
				.<EligibilityDetailsEntity,EligibilityDetails>chunk(3,transMgmr)
				.reader(createReader())
				.processor(processor)
				.writer(createWriter())
				.build();
	}//step
	@Bean(name="job1")
	public Job createJob(JobRepository jobRepo, Step step1) {
		return new JobBuilder("job1",jobRepo)
				.incrementer(new RunIdIncrementer())
				.start(step1)
				.build();
	}
	

}//class
