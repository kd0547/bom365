package com.bom365.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bom365.entity.RegularSupport;

@SpringBootTest
public class RegularSupportServiceTest {
	@Autowired
	RegularSupportService regularSupportService;
	
	
	@Test
	void findId() {
		String id="test2";
		
		RegularSupport regularSupport = regularSupportService.find(id);
		
		System.out.println(regularSupport);
		
	}
	
}
