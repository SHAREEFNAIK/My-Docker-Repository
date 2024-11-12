package com.naik.bindings;

import java.time.LocalDate;

import jakarta.persistence.Column;
import lombok.Data;
@Data
public class EligibilityDetails {
	private Integer edTraceId;
	private Integer caseNo;
	private long holderSSN;
	private String holderName;
	private String planName;
	private String planStatus;
	private LocalDate startDate;
	private LocalDate endDate;
	private Integer benefitAmount;
	private String denialReason;
	private String bankName;
	private int accountNumber;
}
