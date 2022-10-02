package com.bom365.config;


import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import com.bom365.custom.payment.service.KakaoPaySender;
import com.bom365.custom.payment.service.PaymentSender;



@Configuration
public class PaymentConfig {
	
	
	
	@Bean
	public PaymentSender kakaoPay() {
		
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization","KakaoAK c8e090bf8a5148ddc3c97dcd9eed5d6a");
		header.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
		
		KakaoPaySender kakaoPayment = new KakaoPaySender(header);

		return kakaoPayment;
		
	}
	
}
