package com.naik.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naik.entity.CoTriggerEntity;

public interface ICoTriggerRepository extends JpaRepository<CoTriggerEntity, Integer> {

	public CoTriggerEntity findByCaseNo(Integer caseNo);

	public List<CoTriggerEntity> findByCoTriggerStatus(String coTriggerStatus);

}
