package com.naik.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naik.entity.PlanCategory;

public interface ICategoryPlanRepository extends JpaRepository<PlanCategory, Integer> {

}

