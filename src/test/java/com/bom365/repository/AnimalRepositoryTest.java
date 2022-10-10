package com.bom365.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bom365.entity.Animal;

@SpringBootTest
class AnimalRepositoryTest {

	@Autowired
	AnimalRepository animalRepository;
	
	@Test
	void test() {
		ArrayList<Animal> animals =  (ArrayList<Animal>) animalRepository.findAll();
		
		for(Animal animal : animals) {
			System.out.println(animal.toString());
		}
		
	}

}
