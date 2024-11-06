package com.naik.bindings;

import java.time.LocalDate;

import lombok.Data;
@Data
public class PlanData {
		
		private Integer planId;	
		private String planName;	
		private String planDescription;	
		private String activeSw;	
		private LocalDate startDate;
		private LocalDate endDate;
			
	}

