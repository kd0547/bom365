package com.bom365.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/volunteer")
public class VolunteerController {
	
	@GetMapping(value="/main")
	public String volunteerMain() {
		
		
		
		return "volunteer/volunteerDate";
	}
}
