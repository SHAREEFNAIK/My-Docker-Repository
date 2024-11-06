package com.naik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naik.entity.DcIncomeEntity;

public interface IDcIncomeRepository extends JpaRepository<DcIncomeEntity, Integer> {

public DcIncomeEntity findByCaseNo(Integer caseNo);

}
