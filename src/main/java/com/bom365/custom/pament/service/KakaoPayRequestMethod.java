package com.bom365.custom.pament.service;



import java.net.URI;


import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;




import org.springframework.stereotype.Service;

import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;


import com.bom365.custom.pament.dto.ApproveRequestKakaoPayDto;
import com.bom365.custom.pament.dto.ApproveResponseKakaoPayDto;
import com.bom365.custom.pament.dto.CancelRequestKakaopayDto;
import com.bom365.custom.pament.dto.CancelResponseKakaopayDto;
import com.bom365.custom.pament.dto.ReadyRequestKakaopayDto;
import com.bom365.custom.pament.dto.ReadyResponseKakaoPayDto;

import com.fasterxml.jackson.databind.ObjectMapper;


/*

*/



public class KakaoPayRequestMethod {
	//https://intrepidgeeks.com/tutorial/bind-kakapay-api-in-spring-boot
	
	@Autowired
	KakaoPayment kakaoPayment;
	
	
	/*
		-- 전략 패턴을 이용해 리팩토링하기 (취소) 
		-- 
	*/
	//카카오페이 결제 요청
	public ReadyResponseKakaoPayDto payReady(ReadyRequestKakaopayDto readyRequestKakaopayDto) {
		ObjectMapper objectMapper = new ObjectMapper();
		MultiValueMap<String, String> params = kakaoPayment.convert(objectMapper, readyRequestKakaopayDto);
		ReadyResponseKakaoPayDto readyResponseKakaoPayDto = null;
		
		
		try {
			readyResponseKakaoPayDto = kakaoPayment.send(new URI(kakaoPayment.PaymentReady()), params, ReadyResponseKakaoPayDto.class);
		}  catch (RestClientException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return readyResponseKakaoPayDto;
	}
	
	//카카오페이 결제 허용
	public ApproveResponseKakaoPayDto payApprove(ApproveRequestKakaoPayDto approveRequestKakaoPayDto ) {
		ObjectMapper objectMapper = new ObjectMapper();
		MultiValueMap<String, String> params = kakaoPayment.convert(objectMapper, approveRequestKakaoPayDto);
		ApproveResponseKakaoPayDto readyResponseKakaoPayDto = null;
		
		try {
			readyResponseKakaoPayDto = kakaoPayment.send(new URI(kakaoPayment.PaymentApprove()), params, ApproveResponseKakaoPayDto.class);
		}  catch (RestClientException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return readyResponseKakaoPayDto;
	}
	
	//카카오 페이 결제 취소
	public CancelResponseKakaopayDto payCancel(URI uri,CancelRequestKakaopayDto cancelRequestKakaopayDto) throws RestClientException{
		MultiValueMap<String, String> params = delectNullParam(cancelRequestKakaopayDto);

		return (CancelResponseKakaopayDto) send(uri, params, CancelResponseKakaopayDto.class);
	}
	
	private Object send(URI uri,MultiValueMap<String, String> params ,Object object) {
		return kakaoPayment.send(uri, params, object.getClass());
	}
	
	
	private MultiValueMap<String, String> delectNullParam(Object object) {
		ObjectMapper objectMapper = new ObjectMapper();
		kakaoPayment.convert(objectMapper, object);
		
		return null;
	}
	
}
