package com.bom365.entity;

import javax.persistence.*;

import com.bom365.constant.AdoptState;
import com.bom365.constant.Gender;
import com.bom365.dto.AnimalDto;

import lombok.*;


@Entity
@Table(name = "animal")
@Setter
@Getter
@ToString
public class Animal extends BaseEntity{
	@Id
	@Column(name = "animal_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	@Enumerated(EnumType.STRING)
	private AdoptState adoptState;
	
	
	public static Animal createAnimalEntity(AnimalDto animalDto) {
		Animal animal = new Animal();
		
		animal.setAnimalName(animalDto.getAnimalName());
		animal.setAnimalSpecies(animalDto.getAnimalSpecies());
		animal.setAnimalType(animalDto.getAnimalType());
		animal.setNeutering(animalDto.getNeutering());
		animal.setAnimalGender(animalDto.getAnimalGender());
		animal.setAnimalAge(animalDto.getAnimalAge());
		animal.setAnimalWeight(animalDto.getAnimalWeight());
		animal.setAnimalImage(animalDto.getAnimalImage());
		
		//입양 상황 추가 2022.11.09
		animal.setAdoptState(AdoptState.ready);
		
		return animal;
	}
	
}
