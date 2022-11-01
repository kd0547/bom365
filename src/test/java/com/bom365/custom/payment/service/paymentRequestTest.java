package com.bom365.custom.payment.service;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;

import com.bom365.custom.payment.KakopayDto.ReadyRequestKakaopay;
import com.bom365.custom.payment.KakopayDto.ReadyResponseKakaoPay;
import com.bom365.custom.payment.service.KakaoPayRequest;
import com.bom365.custom.payment.service.KakaoPaySender;
import com.bom365.custom.payment.service.PaymentSender;
import com.bom365.custom.payment.service.PaymentURI;
import com.bom365.custom.payment.service.paymentRequest;

@SpringBootTest
class paymentRequestTest {
	
	HttpHeaders httpHeaders = new HttpHeaders();
	
	PaymentSender paymentSender;
	
	paymentRequest paymentRequest;
	
	
	
	@Test
	@DisplayName("kakaopayRequest")
	void test() throws Exception{
		//헤더 등록
		PaymentURI paymentURI = new PaymentURI();
		httpHeaders.add("Authorization","KakaoAK c8e090bf8a5148ddc3c97dcd9eed5d6a");
		httpHeaders.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
		
		
		paymentSender = new KakaoPaySender(httpHeaders);
		//paymentRequest = new KakaoPayRequest(paymentSender);
		
		
		String username = "test";
		int amount = 500;
		
		
		//ReadyRequestKakaopay readyRequestKakaopay = new ReadyRequestKakaopay();
	
		
		
		///ReadyResponseKakaoPayDto readyResponseKakaoPayDto =  (ReadyResponseKakaoPayDto) paymentRequest.payReady(new URI(paymentURI.PaymentReady()), readyRequestKakaopayDto);
		//System.out.println(readyResponseKakaoPayDto.toString());
		
	}

}
