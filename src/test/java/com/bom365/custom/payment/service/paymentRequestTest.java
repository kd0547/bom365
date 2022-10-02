package com.bom365.custom.payment.service;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;

import com.bom365.custom.payment.KakopayDto.ReadyRequestKakaopayDto;
import com.bom365.custom.payment.KakopayDto.ReadyResponseKakaoPayDto;
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
		paymentRequest = new KakaoPayRequest(paymentSender);
		
		
		String username = "test";
		int amount = 500;
		
		
		ReadyRequestKakaopayDto readyRequestKakaopayDto = new ReadyRequestKakaopayDto();
		readyRequestKakaopayDto.setCid("TC0ONETIME");
		readyRequestKakaopayDto.setItem_name("봄365 후원");
		readyRequestKakaopayDto.setPartner_order_id("500"+ "12345");
		readyRequestKakaopayDto.setPartner_user_id(username);
		readyRequestKakaopayDto.setQuantity(1);
		readyRequestKakaopayDto.setTotal_amount(amount);
		readyRequestKakaopayDto.setTax_free_amount(0);
		//url을 config에서 설정할 수 있게 변경하기 
				readyRequestKakaopayDto.setApproval_url("http://localhost:8080/temporary/kakaopayApproval");
				readyRequestKakaopayDto.setCancel_url("http://localhost:8080/temporary/kakaopayCancel");
				readyRequestKakaopayDto.setFail_url("http://localhost:8080/temporary/kakaopayFail");
		
		
		ReadyResponseKakaoPayDto readyResponseKakaoPayDto =  (ReadyResponseKakaoPayDto) paymentRequest.payReady(new URI(paymentURI.PaymentReady()), readyRequestKakaopayDto);
		System.out.println(readyResponseKakaoPayDto.toString());
		
	}

}
