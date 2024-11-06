package com.naik.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ChildrenInputs {

	
	private Integer childrenId;
	private Integer caseNo;
	private LocalDate childDOB;
	private Integer ssn;
	
}
