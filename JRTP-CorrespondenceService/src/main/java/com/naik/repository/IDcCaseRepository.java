package com.naik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naik.entity.DcCaseEntity;
//import com.naik.entity.DcChildrenEntity;

public interface IDcCaseRepository extends JpaRepository<DcCaseEntity, Integer> {

	//public DcChildrenEntity findByCaseNo(Integer caseNo);

}
