package com.bom365.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bom365.constant.AdoptState;
import com.bom365.dto.AdoptQuestionDto;
import com.bom365.entity.AdoptionApplication;
import com.bom365.entity.Animal;
import com.bom365.entity.Member;
import com.bom365.repository.AdoptionRepository;
import com.bom365.repository.AnimalRepository;
import com.bom365.repository.MemberRepository;

@Service
public class AdoptionService {
	
	@Autowired
	AdoptionRepository adoptionRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	AnimalRepository animalRepository;
	
	@Transactional
	public void saveAdopt(String supportId, AdoptQuestionDto adoptQuestionDto) {
		Member member = memberRepository.findBySupporterId(supportId);
		Optional<Animal> animal = animalRepository.findById(adoptQuestionDto.getAnimalId());
		
		animal.get().setAdoptState(AdoptState.process);
		
		AdoptionApplication adoptionApplication = AdoptionApplication.create(member,animal.get(),adoptQuestionDto);
		
		adoptionRepository.save(adoptionApplication);
		
	}
	
	public void cancelAdopt(Long adoptId) {
		Optional<AdoptionApplication> adoptionApplication = adoptionRepository.findById(adoptId);
		
		AdoptionApplication findAdopt =  adoptionApplication.get();
		
		findAdopt.setMember(null);
		findAdopt.getAnimal().setAdoptState(AdoptState.ready);
		
		adoptionRepository.delete(findAdopt);
		
	}
	
	
	
	
}
