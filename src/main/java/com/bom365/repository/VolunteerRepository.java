package com.bom365.repository;

import java.util.List;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bom365.entity.Volunteer;



public interface VolunteerRepository extends JpaRepository<Volunteer, Long>{
	
	
	
	@Transactional
	List<Volunteer> findVolunteerListBySupporterId(String supporterId);
}
