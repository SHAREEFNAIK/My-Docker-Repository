package com.naik.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name="JRTP_CASE_ENTITY")
@Data
public class DcCaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer caseId;
	
	private Integer caseNo;
	private Integer planId;
	private Integer appId;
	

}
