package com.naik.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="JRTP_CO_TRIGGER_ENTITY")
@Data
public class CoTriggerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer coTriggerId;
	private Integer caseNo;
	@Lob
	private byte[] coNoticePdf;
	@Column(length=40)
	private String CoTriggerStatus="pending";
	
	

}
