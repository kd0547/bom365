package com.bom365.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bom365.entity.Amount;
import com.bom365.entity.Animal;

public interface AmountRepository extends JpaRepository<Amount, Long >{

}
