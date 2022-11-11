package com.bom365.service;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AgeStringTest {
	
	@Test
	void doubleToStringTest() {
		
		
		System.out.println(ageDoubleToString(0.1));
		
		
		
		
	
		//System.out.println();
		
		
		
	}
	public String ageDoubleToString(double age) {
		double 	doubleAge 	=  age;
		int 	intAge 		= (int)age;
		String year = null,month = null;
		BigDecimal _doubleAge = new BigDecimal(String.valueOf(doubleAge));
		BigDecimal _intAge = new BigDecimal(String.valueOf(intAge));
		int int_month = _doubleAge.subtract(_intAge).multiply(BigDecimal.TEN).multiply(BigDecimal.TEN).intValue();
		
		if(intAge != 0) {
			year = String.valueOf(intAge);
		}
		month = String.valueOf(int_month) ;
		
		
		if(year == null) {
			return month + "개월";
		}
		
		if(month.equals("0")) {
			return year + "살 ";
		}
		
		if(year != null && month != null) {
			return year + "살 " + month + "개월";
		}
		return null;
	}
}
