package com.bom365.controller;

import java.util.concurrent.Callable;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bom365.common.SecurityLogger;
import com.bom365.service.SampleService;

@Controller
public class SampleContoller {
	@GetMapping("/async-handler")
	@ResponseBody
	public Callable<String> asyncHandler() {
		
		SecurityLogger.log("MVC");
		
		return new Callable<String>() {

			@Override
			public String call() throws Exception {
				SecurityLogger.log("Callable");
				return "Async Handler";
			}
			
		};
	}
	@GetMapping("/async-Service")
	@ResponseBody
	public String asyncService() {
		
		SecurityLogger.log("MVC,before Async Service");
		SampleService.asyncService();
		SecurityLogger.log("MVC,after Async Service");
		
		return "Async Service";
	}
	
	
	
}
