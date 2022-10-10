package com.bom365.entity;

import javax.persistence.*;


import com.bom365.constant.Gender;

import lombok.*;


@Entity
@Table(name = "animal")
@Setter
@Getter
@ToString
public class Animal extends BaseEntity{
	@Id
	@Column(name = "animal_id")
	private Long id;
	
	
	private String animalName;
	
	private String animalSpecies;
	
	private String animalType;
	
	private String Neutering;
	
	@Enumerated(EnumType.STRING)
	private Gender animalGender;
	
	private double animalAge;
	
	private double animalWeight;
	
	private String animalImage;
	
}
