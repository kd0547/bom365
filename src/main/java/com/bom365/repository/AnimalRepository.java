package com.bom365.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.bom365.entity.Animal;


public interface AnimalRepository extends JpaRepository<Animal, Long>, AnimalCustomRepository {
	
	
}

