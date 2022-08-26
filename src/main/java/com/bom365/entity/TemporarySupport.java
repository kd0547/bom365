package com.bom365.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Temporary")
public class TemporarySupport extends BaseEntity{
	
	@Id
	@Column(name = "TemporaryId")
	private Long id;
	
}
