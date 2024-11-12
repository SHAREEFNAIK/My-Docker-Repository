package com.naik.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "JRTP_CASE_WORKERS_ENTITY")
@Data
public class CaseWorkerEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer accountId;
	@Column(name = "FULL_NAME",length=50)
	private String fullName;
	@Column(name = "EMAIL",length=20)
	private String email;
	@Column(name = "PASSWORD",length=20)
	private String pwd;
	@Column(name = "GENDER",length=10)
	private String gender;
	private Integer SSN;
	private LocalDate DOB;
	@Column(name = "CREATION_DATE", updatable = false)
	@CreationTimestamp()
	private LocalDateTime creationDate;
	@Column(name = "UPDATED_DATE", updatable = true, insertable = false)
	private LocalDateTime updatedDate; 
	@Column(name="CREATED_BY", length = 30)
	private String createdBy;
	@Column(name="UPDATED_BY", length = 30) 
	private String updatedBy;
	  
	  
}
