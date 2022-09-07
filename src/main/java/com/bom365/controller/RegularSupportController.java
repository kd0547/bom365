package com.bom365.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/regular")
public class RegularSupportController {
	
	@GetMapping(value="/payment") 
	public String reqularMain(){
		
		
		return "error/404";
		//return "support/regularSupport";
	}

}
