package com.naik.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.naik.Exception.InvalidSSNException;
import com.naik.bindings.AppRegistrationInputs;
import com.naik.entity.ApplicationRegistrationEntity;
import com.naik.repository.IAppRegistrationRepository;

import reactor.core.publisher.Mono;

@Service
public class ApplicationRegistrationServiceimpl implements IAppRegistrationService {

	@Autowired
	private IAppRegistrationRepository appRepo;
	@Autowired
	private WebClient client;
	
	@Value("${ar.ssn-web.url}")
	private String targetUrl;
	@Value("${ar.state}")
	private String targetState;
	
	
	
	@Override
	public Integer registerCitizenApplication(AppRegistrationInputs inputs) throws InvalidSSNException{
		//perform webservice call to check ssn valid or not and to get target state name
		System.out.println("=============ApplicationRegistrationServiceimpl.registerCitizenApplication()====start");
		Mono<String> response=client.get().uri(targetUrl,inputs.getSsn()).retrieve()
				.onStatus(HttpStatus.BAD_REQUEST::equals, res->res.bodyToMono(String.class).map(ex->new InvalidSSNException("invalid ssn"))).bodyToMono(String.class);
		System.out.println("line36,37 got executed");
		String stateName=response.block();
		System.out.println("==========line 39 got executed=====state found");

		//register citizen if he belong to california state
		if(stateName.equalsIgnoreCase(stateName)) {
			// prepare entity object 
			System.out.println("==========control entered in if====state found");

			ApplicationRegistrationEntity entity= new ApplicationRegistrationEntity();
			BeanUtils.copyProperties(inputs,entity);
			entity.setStateName(stateName);
			System.out.println(" ========if about to  complete=========");
			return appRepo.save(entity).getAppId();
			
		}//if
		else
		{
			System.out.println("=======control entered in else========");
		throw new InvalidSSNException("invalid SSN");
		}
	}

}
