package com.naik.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naik.entity.CaseWorkerEntity;

public interface ICaseWorkerRepository extends JpaRepository<CaseWorkerEntity, Integer> {

}
