package com.bom365.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/temporary")
public class TemporarySupportController {
	
	
	@GetMapping(value="/payment")
	public String temporaryMain() {
		
		return "support/temporarySupport";
	}
}
