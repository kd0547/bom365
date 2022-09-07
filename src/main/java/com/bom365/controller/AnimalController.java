package com.bom365.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/animal")
public class AnimalController {
	
	@GetMapping(value="/list")
	public String animalList() {
		
		return "error/404";
		//return "animal/animalList";
	}

}
