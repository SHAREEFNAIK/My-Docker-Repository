package com.naik.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="JRTP_PLAN_MASTER")
public class PlanEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column()
	private Integer palnId;
	
	@Column(length = 50)
	private String planName;
	@Column(length = 50)
	private String palnDescription;
	@Column(length = 10)
	private String activeSw="active";
	
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	
	@Column(updatable = false)
	@CreationTimestamp
	private LocalDateTime creationDate ;
	
	@Column(insertable = false)
	@UpdateTimestamp
	private LocalDateTime updatedDate ;

	  @Column(length=30)
	  private String createdBy; 
	  @Column(length=30) 
	  private String updatedBy;
	 
}
