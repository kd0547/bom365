package com.bom365.custom.payment.service;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bom365.custom.payment.NaverDto.ReadyRequestNaverpayDto;
import com.bom365.custom.payment.NaverDto.ReadyResponseNaverpayDto;

@SpringBootTest
public class NaverpaySenderTest {
	private PaymentSender naverpay = new NaverPaySender();
	
	@Test
	public void naverpaySendTest() throws Exception{
		//URL
		String URL = "https://dev.apis.naver.com/kd2935/naverpay/payments/v2/reserveHTTP/1.1";
		String returnURL = "http://localhost:8080";
		
		
		
		ReadyRequestNaverpayDto readyRequestNaverpayDto = new ReadyRequestNaverpayDto();
		readyRequestNaverpayDto.setMerchantUserKey("test");
		readyRequestNaverpayDto.setProductName("결제 테스트");
		readyRequestNaverpayDto.setProductCount(1);
		readyRequestNaverpayDto.setTotalPayAmount(500);
		readyRequestNaverpayDto.setTaxScopeAmount(0);
		readyRequestNaverpayDto.setTaxExScopeAmount(0);
		readyRequestNaverpayDto.setReturnUrl(returnURL);
		
		naverpay.send(new URI(URL), readyRequestNaverpayDto, ReadyResponseNaverpayDto.class);
		//System.out.println(readyResponseNaverpayDto.toString());
		
		
		
	}
}
