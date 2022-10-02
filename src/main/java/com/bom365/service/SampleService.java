package com.bom365.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bom365.common.SecurityLogger;

@Service
public class SampleService {
	
	@Async
	public static void asyncService() {
		SecurityLogger.log("Async service");
		System.out.println("Async service called");
	}


}
