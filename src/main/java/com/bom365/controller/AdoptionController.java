package com.bom365.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bom365.dto.AdoptQuestionDto;
import com.bom365.service.AdoptionService;

@Controller
@RequestMapping("/adopt")
public class AdoptionController {
	
	@Autowired
	AdoptionService adoptionService;
	
	@GetMapping("/ready")
	public String adoptionReady(@RequestParam(value = "animalId") Long animalId,@RequestParam(value = "name") String name, Model model) {
		
		AdoptQuestionDto adoptQuestionDto = new AdoptQuestionDto();
		
		adoptQuestionDto.setAnimalId(animalId);
		adoptQuestionDto.setAnimalName(name);
		
		
		model.addAttribute("adoptQuestionDto",adoptQuestionDto);
		
		return "adoption/adoptionSignup";
	}
	
	@PostMapping("/signup")
	public String adoptionSignup(Principal principal, AdoptQuestionDto adoptQuestionDto) {
		
		adoptionService.saveAdopt(principal.getName(),adoptQuestionDto);
		
		//System.out.println("supportId : "+principal.getName()+", "+adoptQuestionDto.toString());
		
		return "main";
	}
	
	@GetMapping("/cancel")
	public String adoptCancel(@RequestParam(value = "adoptId")Long adoptId) {
		
	//	System.out.println(adoptId);
		adoptionService.cancelAdopt(adoptId);
		
		//member/mypageForm
		return "redirect:/member/update";
	}
	
	
}
