package com.bom365.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bom365.dto.AnimalDto;
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
			
		}
		
		if(!animalSearchDto.isAllEmpty()) {
			animalSearchDto.changeEmptyFieldNull();
			
			
			List<Animal> searchAnimals= animalService.SearchAnimal(animalSearchDto);
			
			model.addAttribute("animalSearchDto", new AnimalSearchDto());
			model.addAttribute("animals",searchAnimals);
		}
		

		
		return "supportAnimal/supportList";
	}
	
	@GetMapping("/animal/{animalId}")
	public String animalSelect(@PathVariable("animalId")Long id,Model model) {
		
		try {
			AnimalDto animalDto = animalService.findById(id);
			
			model.addAttribute("animalDto", animalDto);
			
			return "supportAnimal/supportOne";
			
		} catch (NoSuchElementException e) {
			model.addAttribute("message", "찾으시는 데이터가 없습니다.");
			
			//리다이렉트 과정
			//https://shanepark.tistory.com/370
			return "redirect:/animal/list";
		}
	}
	

}
