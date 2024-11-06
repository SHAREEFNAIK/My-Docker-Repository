package com.naik.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naik.entity.CoTriggerEntity;

public interface ICoTriggerRepository extends JpaRepository<CoTriggerEntity, Integer> {

	List<CoTriggerEntity> findByTriggerStatus(String string);

	public CoTriggerEntity findByCaseNo(Integer caseNo);

}
