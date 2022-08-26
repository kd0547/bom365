package com.bom365.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Regular")
public class RegularSupport extends BaseEntity{
	@Id
	@Column(name = "RegularId")
	private Long id;
}
