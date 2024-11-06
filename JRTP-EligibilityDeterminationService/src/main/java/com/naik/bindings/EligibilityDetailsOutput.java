package com.naik.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EligibilityDetailsOutput {
	private String holderName;
	private String planName;
	private String planStatus;
	private LocalDate startDate;
	private LocalDate endDate;
	private Integer benefitAmount;
	private String denialReason;

}
