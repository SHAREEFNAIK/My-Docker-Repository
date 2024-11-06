package com.naik.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="JRTP_ELIGIBILITY_ENTITY")
@Data
public class EligibilityDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer edTraceId;
	
	private Integer caseNo;
	private long holderSSN;
	
	@Column(length=40)
	private String holderName;
	@Column(length=40)
	private String planName;
	@Column(length=40)
	private String planStatus;
	
	private LocalDate startDate;
	private LocalDate endDate;
	private Integer benefitAmount;
	@Column(length=40)
	private String denialReason;

}
