package com.naik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naik.entity.ApplicationRegistrationEntity;

public interface IAppRegisterRepository extends JpaRepository<ApplicationRegistrationEntity, Integer> {

}
