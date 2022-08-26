package com.bom365.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "animal")
public class Animal extends BaseEntity{
	@Id
	@Column(name = "animalID")
	private Long id;
}
