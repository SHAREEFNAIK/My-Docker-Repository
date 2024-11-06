package com.naik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naik.entity.DcEducationEntity;

public interface IDcEducationRepository extends JpaRepository<DcEducationEntity, Integer> {

	public DcEducationEntity findByCaseNo(Integer caseNo);

}
