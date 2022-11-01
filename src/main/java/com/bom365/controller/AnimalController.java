package com.bom365.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bom365.dto.AnimalSearchDto;
import com.bom365.entity.Animal;
import com.bom365.service.AnimalService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/animal")
public class AnimalController {
	
	private final AnimalService animalService;
	
	@GetMapping(value="/list")
	public String animalList(AnimalSearchDto animalSearchDto,Model model) {
		
		
		// 검색 결과가 없을 때 OR 첫 페이지 
		if(animalSearchDto.isAllEmpty()) {
			List<Animal> AllAnimal = animalService.findAllAnimal();
			
			model.addAttribute("animalSearchDto", new AnimalSearchDto());
			model.addAttribute("animals",AllAnimal);
			
			return "animal/animalList";
		}
		
		if(!animalSearchDto.isAllEmpty()) {
			animalSearchDto.changeEmptyFieldNull();
			
			
			List<Animal> searchAnimals= animalService.SearchAnimal(animalSearchDto);
			
			model.addAttribute("animalSearchDto", new AnimalSearchDto());
			model.addAttribute("animals",searchAnimals);
			
			return "animal/animalList";
		}
		

		
		return "/error/404";
	}
	
	

}
