package com.naik.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "JRTP_APPLICATION_REGISTRATION")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRegistrationEntity {
	@Id
	@SequenceGenerator(name="gen_seq",sequenceName = "app_id_seq",initialValue = 1000,allocationSize  =1)
	@GeneratedValue(generator = "gen-seq",strategy = GenerationType.SEQUENCE)
	private Integer appId;
	@Column(length=30)
	private String fullName;
	@Column(length=30)
	private String email;
	@Column(length=30)
	private String gender;
	private Long phoneNo;
	private LocalDate dob;
	private Long ssn;
	@Column(length=30)
	private String stateName;
	@Column(length=30)
	private String createdBy="shareef naik";
	@Column(length=30)
	private String updatedBy="sagar naik";
	@CreationTimestamp()
	@Column(updatable = false)
	private String creationDate;
	@UpdateTimestamp()
	@Column(insertable  =false)
	private String updationDate;
	
}
