package com.bom365.repository;

import java.util.List;

import com.bom365.dto.AnimalSearchDto;
import com.bom365.entity.Animal;

public interface AnimalCustomRepository {
	public List<Animal> searchWhereAnimal(AnimalSearchDto animalSearchDto);
}
