package com.bom365.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bom365.custom.pament.service.KakaoPayment;



@Configuration
public class PaymentConfig {
	
	
	
	@Bean
	public KakaoPayment test() {
		KakaoPayment kakaoPayment = new KakaoPayment();
		
		
		
		return kakaoPayment;
		
	}
	
}
