package com.bom365.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/support")
public class SupportController {
	
	@GetMapping("/main")
	public String supportMain() {
		
		return "support/supportMain";
	}

}
