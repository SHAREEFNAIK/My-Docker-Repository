package com.naik.bindings;

import java.time.LocalDate;
import lombok.Data;

@Data
public class CitizensRegistrationInputs {
		
	private String fullName;
	private String email;
	private String gender;
	private Long phoneNo;
	private LocalDate dob;
	private Long ssn;

}
