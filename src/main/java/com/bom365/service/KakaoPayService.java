package com.bom365.service;





import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.bom365.payment.dto.ReadyRequestKakaopayDto;
import com.bom365.payment.dto.ReadyResponseKakaoPayDto;


@Service
public class KakaoPayService {
	
	
	
	//카카오페이 결제 요청
	public ReadyResponseKakaoPayDto payReady(int totalAmount,String UserId) {
		//리팩토링하기 
		//https://intrepidgeeks.com/tutorial/bind-kakapay-api-in-spring-boot
		
		String url= "https://kapi.kakao.com/v1/payment/ready";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization","AdminKey");
		
		
		headers.add("Content-type",MediaType.APPLICATION_JSON_VALUE);
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		ReadyRequestKakaopayDto readyRequestKakaopayDto = new ReadyRequestKakaopayDto();
		
		readyRequestKakaopayDto.setCid("imp44896858");
		readyRequestKakaopayDto.setItem_name("봄365 후원");
		readyRequestKakaopayDto.setPartner_order_id("500"+ "12345");
		readyRequestKakaopayDto.setPartner_user_id(UserId);
		readyRequestKakaopayDto.setQuantity(1);
		readyRequestKakaopayDto.setTotal_amount(totalAmount);
		readyRequestKakaopayDto.setTax_free_amount(0);
		readyRequestKakaopayDto.setApproval_url("http://localhost/order/pay/completed");
		readyRequestKakaopayDto.setCancel_url("http://localhost/order/pay/cancel");
		readyRequestKakaopayDto.setFail_url("http://localhost/order/pay/fail");
		
		
		HttpEntity<ReadyRequestKakaopayDto> request = new HttpEntity<>(readyRequestKakaopayDto,headers);
		
		System.out.println("HEADER : "+request.getHeaders());
		
		try {
			ReadyResponseKakaoPayDto readyResponseKakaoPayDto = restTemplate.postForObject(url, request, ReadyResponseKakaoPayDto.class);
			
		} catch (RestClientException e) {
			e.printStackTrace();
		} 
		
		
		//System.out.println(readyResponseKakaoPayDto.toString());
		
		return null;
	}
}
