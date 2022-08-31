package com.bom365.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;


@Entity
@Table(name="volunteer")
@Getter
@Setter
@ToString
public class Volunteer extends BaseEntity{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="supporterId")
	private String supporterId;
	
	@Column(nullable = false )
	private String supporterName;
	
	@Column(nullable = false )
	private String phoneNumber;
	
	
	private LocalDateTime startDate;
	
	
	private LocalDateTime startTime;
}
