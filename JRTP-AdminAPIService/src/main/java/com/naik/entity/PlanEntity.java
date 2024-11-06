package com.naik.entity;

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
	@Column(name="PLAN_ID")
	private Integer planId;	
	@Column(name="PLAN_NAME",length = 30)
	private String planName;	
	@Column(name="PLAN_BUDGET",length = 30)
	private String planBudget;	
	@Column(name="PLAN_dESCRIPTION",length = 100)
	private String planDescription;	
	@Column(name="PLAN_CATEGORY_ID")
	private Integer planCategoryId;	
	@Column(name="ACTIVE_SW", length = 30)
	private String activeSw="active";	
	@Column(name="CREATION_DATE",updatable = false)
	@CreationTimestamp
	private LocalDateTime creationDate ;	
	@Column(name="UPDATED_DATE",updatable = true,insertable = false)
	@UpdateTimestamp
	private LocalDateTime updatedDate ;	  
	  @Column(name="CREATED_BY", length = 30)
	  private String createdBy;	  
	  @Column(name="UPDATED_BY", length = 30) 
	  private String updatedBy;
	  
	  
	
	  
	
}
