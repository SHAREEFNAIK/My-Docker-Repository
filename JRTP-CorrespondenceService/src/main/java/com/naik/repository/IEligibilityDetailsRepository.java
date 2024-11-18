package com.naik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naik.entity.EligibilityDetailsEntity;

public interface IEligibilityDetailsRepository extends JpaRepository<EligibilityDetailsEntity, Integer> {

	public  EligibilityDetailsEntity findByCaseNo(Integer caseNo);

}
