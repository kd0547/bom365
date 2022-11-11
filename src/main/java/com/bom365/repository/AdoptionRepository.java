package com.bom365.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bom365.entity.AdoptionApplication;

public interface AdoptionRepository extends JpaRepository<AdoptionApplication, Long>{

}
