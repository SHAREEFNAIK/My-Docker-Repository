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
@Table(name="TRAVEL_PALN")
public class TravelPlan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PALN_ID")
	private Integer palnId;
	
	@Column(name="PALN_NAME",length = 30)
	private String planName;
	
	@Column(name="PALN_BUDGET",length = 30)
	private String palnBudget;
	
	@Column(name="PALN_dESCRIPTION",length = 30)
	private String palnDescription;
	
	@Column(name="PALN_CATEGORY_ID")
	private Integer palnCategoryId;
	
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
