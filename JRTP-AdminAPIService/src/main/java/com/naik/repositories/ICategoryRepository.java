package com.naik.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naik.entity.CategoryEntity;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Integer> {

}

