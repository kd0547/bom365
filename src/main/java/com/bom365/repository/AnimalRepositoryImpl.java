package com.bom365.repository;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.bom365.constant.Gender;
import com.bom365.dto.AnimalSearchDto;
import com.bom365.entity.Animal;


import static  com.bom365.entity.QAnimal.*;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class AnimalRepositoryImpl implements AnimalCustomRepository{
	private final JPAQueryFactory jpaQueryFactory;
	
	
	public AnimalRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
		this.jpaQueryFactory = jpaQueryFactory;
	}

	

	@Override
	public List<Animal> searchWhereAnimal(AnimalSearchDto animalSearchDto) {
		return jpaQueryFactory.select(animal)
				.from(animal)
				.where( animalNameEq(animalSearchDto.getAnimalName()),
						animalSpeciesEq(animalSearchDto.getAnimalSpecies()),
						animalGenderEq(animalSearchDto.getAnimalGender()),
						animalWeightBetween(animalSearchDto.getAnimalWeight()),
						animalAgeBetween(animalSearchDto.getAnimalAge())
						)
				.fetch();
	}
	
	private Predicate animalNameEq(String animalName) {
		
		return animalName == null ? null : animal.animalName.eq(animalName);
	}
	
	private Predicate animalSpeciesEq(String animalSpecies) {
		return animalSpecies == null ? null : animal.animalSpecies.eq(animalSpecies);
	}
	
	private Predicate animalGenderEq(Gender animalGender) {
		return animalGender == null ? null : animal.animalGender.eq(animalGender);
	}
	
	private Predicate animalWeightBetween(String weight) {		
		return weight == null ? null : animal.animalWeight.between(min('~', weight), max('~',weight));
	}
	
	private Predicate animalAgeBetween(String age) {
		return  age == null ? null : animal.animalAge.between(min('~', age), max('~',age));
	}
	
	
	
	private double min(char point,String str) {
		int source = str.indexOf(point);
		int defaultMin = 0;
		
		String str2 = str.substring(defaultMin, source);
		
		return Double.parseDouble(str2);
	}
	
	
	private double max(char point,String str) {
		int source = str.indexOf(point);
		int end = str.length();
		
		String str2 = str.substring(source+1, end);
		
		return Double.parseDouble(str2);
	}
	
}
