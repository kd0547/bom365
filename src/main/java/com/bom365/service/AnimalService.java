package com.bom365.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bom365.dto.AnimalDto;
import com.bom365.dto.AnimalSearchDto;
import com.bom365.entity.Animal;
import com.bom365.repository.AnimalRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnimalService {
	
	private final AnimalRepository animalRepository;
	
	public List<Animal> findAllAnimal() {
		return animalRepository.findAll();
	}
	
	//http://querydsl.com/
	public List<Animal> SearchAnimal(AnimalSearchDto animalSearchDto) {
		return animalRepository.searchWhereAnimal(animalSearchDto);
		
	}
	
	public AnimalDto findById(Long id) {
		Animal findAnimal = animalRepository.findById(id).get();
		
		AnimalDto animalDto = new AnimalDto();
		
		animalDto.setId(findAnimal.getId());
		animalDto.setAnimalType(findAnimal.getAnimalType());
		animalDto.setAnimalName(findAnimal.getAnimalName());
		animalDto.setNeutering(findAnimal.getNeutering());
		animalDto.setAnimalSpecies(findAnimal.getAnimalSpecies());
		animalDto.setAnimalGender(findAnimal.getAnimalGender());
		animalDto.setAnimalAgeString(ageDoubleToString(findAnimal.getAnimalAge()));
		
		animalDto.setAdoptState(findAnimal.getAdoptState());
		
		animalDto.setAnimalWeight(findAnimal.getAnimalWeight());
		animalDto.setAnimalImage(findAnimal.getAnimalImage());
		
		
		
		return animalDto;
	}
	
	public String ageDoubleToString(double age) {
		double 	doubleAge 	=  age;
		int 	intAge 		= (int)age;
		String year = null,month = null;
		BigDecimal _doubleAge = new BigDecimal(String.valueOf(doubleAge));
		BigDecimal _intAge = new BigDecimal(String.valueOf(intAge));
		int int_month = _doubleAge.subtract(_intAge).multiply(BigDecimal.TEN).multiply(BigDecimal.TEN).intValue();
		
		if(intAge != 0) {
			year = String.valueOf(intAge);
		}
		month = String.valueOf(int_month) ;
		
		
		if(year == null) {
			return month + "개월";
		}
		
		if(month.equals("0")) {
			return year + "살 ";
		}
		
		if(year != null && month != null) {
			return year + "살 " + month + "개월";
		}
		return null;
	}
	
}
