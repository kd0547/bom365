package com.bom365.service;

import java.util.List;


import org.springframework.stereotype.Service;

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
	
}
