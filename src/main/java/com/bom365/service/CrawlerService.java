package com.bom365.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bom365.entity.Animal;
import com.bom365.repository.AnimalRepository;

@Service
@Transactional
public class CrawlerService {
	
	@Autowired
	AnimalRepository animalRepository;
	
	public boolean findData() {
		Long count = animalRepository.count(); 
		
		return count == 0;
	}
	
	public List<Animal> saveAll(List<Animal> animalList) {
		return animalRepository.saveAll(animalList);
	}
	
	
	
}
