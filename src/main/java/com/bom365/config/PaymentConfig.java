package com.bom365.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bom365.custom.pament.service.KakaoPayment;



@Configuration
public class PaymentConfig {
	
	
	
	@Bean
	public KakaoPayment test() {
		KakaoPayment kakaoPayment = new KakaoPayment();
		kakaoPayment.header.add("Authorization","KakaoAK c8e090bf8a5148ddc3c97dcd9eed5d6a");
		kakaoPayment.header.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
		
		
		return kakaoPayment;
		
	}
	
}
