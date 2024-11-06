package com.naik.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="JRTP_CHILDREN_ENTITY")
@Data
public class DcChildrenEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer childrenId;
	
	private Integer caseNo;
	private LocalDate childDOB;
	private Integer ssn;
	
}
