package com.naik.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naik.entity.TravelPlan;

public interface ITravelPlanRepository extends JpaRepository<TravelPlan, Integer> {

}
